import React from "react";
import { Table, Button, Form, ButtonGroup, Nav, Navbar } from "react-bootstrap";
import FestivalAxios from "../../apis/FestivalAxios";


class Festival extends React.Component {
    constructor(props) {
        super(props);

        let festival = {
            naziv: "",
            datumPocetka: "",
            datumZavrsetka: "",
            cena: 0,
            dostupneKarte: 0,
            mestoId: this.props.match.params.id,
            
        };

        this.state = {
            festival: festival,
            festivali: [],
            mesta: [],
            search: { mestoId: -1, naziv: "" },
            pageNo: 0,
            totalPages: 2,
            avaliable : false,
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getMesta();
        await this.getFestivali(0);
    }

    async getFestivali(page) {
        let config = {
            params: {
                pageNo: page
            }
        };

        if(this.state.search.mestoId != -1) {
            config.params.mestoId = this.state.search.mestoId;
        }
        if(this.state.search.naziv != "") {
            config.params.naziv = this.state.search.naziv;
        }
        try{
            let result = await FestivalAxios.get("/festivali", config);
            if (result && result.status === 200){
                this.setState({
                    pageNo: page,
                    festivali: result.data,
                    totalPages: result.headers["total-pages"],
                });
            }
        }catch (error) {
            alert("Nije uspelo dobavljanje.");
        }
    }

    async getMesta(){
        try{
            let result = await FestivalAxios.get("/mesta");
            if(result && result.status === 200) {
                this.setState({
                    mesta: result.data,
                });
            }
        }catch (error) {
            alert("Nije uspelo dobavljanje");
        }
    }

    goToRezervacija(festivalId) {
        this.props.history.push("/festivali/rezervisi/" + festivalId);
    }

    goToAdd() {
        this.props.history.push("/festivali/add/");
    }

    async doDelete(festivalId){
        try{
            await FestivalAxios.delete("/festivali/" + festivalId);
            this.getFestivali(0);
        }catch (error) {
            alert("Nije uspelo brisanje.");
        }
    }



    searchValueInputChange(event){
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let search = this.state.search;
        search[name] = value;

        this.setState({ search: search });
    }

    doSearch(){
        this.getFestivali(0);
    }

    async doMesto(event, festivalId, festival ) {
        try {
        let control = event.target;
        let mestoId = control.value;
        
        console.log(festival)
        console.log(mestoId)
        if(!mestoId || mestoId === -1){
            return;
        }
        festival.mestoId = mestoId
          await FestivalAxios.put(`/festivali/${festivalId}`, festival);
          this.props.history.push("/festivali");
        } catch (error) {
          alert("Nije uspelo čuvanje.");
        }
      }

      hiddenForm() {
        const curentState = this.state.avaliable;
        this.setState({avaliable: !curentState});
      }

      check(festival){

        if( !festival.dostupneKarte || festival.dostupneKarte <= 0){

            return true;
      }
      if( new Date(festival.datumZavrsetka) < new Date()){
          
        return true;
      }
      return false;
    }


    render() {
        return (
            <div>
                <h1>Festivali</h1>

                <Form style={{ marginTop: 35 }}>
                    <Form.Group>
                        <Form.Label>Mesto odrzavanja</Form.Label>
                        <Form.Control
                            onChange={(e) => this.searchValueInputChange(e)}
                            onClick={() => this.doSearch()}
                            value={this.state.search.mestoId}
                            name="mestoId"
                            as="select"
                        >
                            <option value={-1}></option>
                            {this.state.mesta.map((mesto) => {
                                return (
                                    <option value={mesto.id} key={mesto.id}>
                                        {mesto.grad},{mesto.drzava}
                                    </option>
                                );
                            })}
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Naziv</Form.Label>
                        <Form.Control
                            value={this.state.search.naziv}
                            name="naziv"
                            as="input"
                            onChange={(e) => this.searchValueInputChange(e)}
                            onKeyUp={() => this.doSearch()}>
                        </Form.Control>
                    </Form.Group>
                    
                </Form>
                <Navbar>
                    <Nav className="mr-auto">
                    <ButtonGroup  style={{ marginTop: 25 }}>
                <Button variant="success" onClick={() => this.goToAdd()}>
                            Kreiraj festival
                        </Button>
                        </ButtonGroup>
                        </Nav>
                        <Nav>
                        <ButtonGroup style={{ marginTop: 25 }}>
                    <Button
                        disabled={this.state.pageNo== 0} onClick={() => this.getFestivali(this.state.pageNo - 1)}>
                        Prethodna
                    </Button>
                    <Button
                        disabled={this.state.pageNo==this.state.totalPages - 1} onClick={() => this.getFestivali(this.state.pageNo + 1)}>
                        Next
                    </Button>
                    </ButtonGroup>
                    </Nav>
                </Navbar>
                    
                <Table bordered striped variant="dark" style={{ marginTop: 5 } }>
                    <thead className="thead-dark">
                        <tr>
                            <th>Naziv festivala</th>
                            <th>Mesto odrzavanja</th>
                            <th>Datum pocetka festivala</th>
                            <th>Datum zavrsetka festivala</th>
                            <th>Cena karte (RSD)</th>
                            <th>Broj preostalih karata</th>
                            <th colSpan={2}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.festivali.map((festival) => {
                            return (
                                <tr key={festival.id}>
                                    <td>{festival.naziv}</td>
                                    <td>
                                    <select onChange={(e) => this.doMesto(e, festival.id, festival, this.state.search.mestoId)}
                            value={festival.mestoId}
                            name="mestoId"
                            as="select"><option value={this.props.match.params.id}></option>{this.state.mesta.map((mesto) => {
                                return (
                                    <option value={mesto.id} key={mesto.id}>{mesto.grad},{mesto.drzava}</option>
                                );
                            })}</select>
                                    </td>
                                    <td>{festival.datumPocetka}</td>
                                    <td>{festival.datumZavrsetka}</td>
                                    <td>{festival.cena}</td>
                                    <td>{festival.dostupneKarte}</td>
                                    <td>
                                        <Button
                                            hidden= {this.check(festival)}
                                            variant="info"
                                            className= {this.state.avaliable ? 'hidden' : null}
                                            onChange={() => this.hiddenForm()}
                                            onClick={() => this.goToRezervacija(festival.id)}
                                            >
                                    
                                            Rezerviši

                                        </Button>

                                        <Button
                                            variant="danger"
                                            onClick={() => this.doDelete(festival.id)}
                                            style={{ marginLeft: 5 }}>
                                            Obriši
                                </Button>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </Table>
            </div>
        );
    }
}

    export default Festival;
