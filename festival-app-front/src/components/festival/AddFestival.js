import React from "react";
import {Button, Form} from "react-bootstrap";

import FestivalAxios from "../../apis/FestivalAxios";

class AddFestival extends React.Component {
    constructor(props){
        super(props);

        let festival = {
            naziv: "",
            datumPocetka: "",
            datumZavrsetka: "",
            cena: 0,
            dostupneKarte: 0,
            mestoId: -1,
        };

        this.state= {
            festival: festival,
            festivali: [],
            mesta: []
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getMesta();
        await this.getFestivali();
    }

    async getFestivali(){
        try{
            let festival = this.state.festival;
            let festivalDTO = {
                naziv: festival.naziv,
                datumPocetka: festival.datumPocetka,
                datumZavrsetka: festival.datumZavrsetka,
                cena: festival.cena,
                mestoId: festival.mestoId
            }
            let result = await FestivalAxios.get("/festivali", festivalDTO);
            if(result && result.status === 200){
                this.setState({
                    festivali: result.data
                });
            }
            } catch(error){
                alert("Nije uspelo dobavljanje.");
            }
        }

        async getMesta(){
            try {
                let result = await FestivalAxios.get("/mesta");
                if(result && result.status === 200){
                    this.setState({
                        mesta: result.data,
                    });
                }
            }catch(error){
                alert("Nije uspelo dobavljanje.");
            }
        }

        async doAdd() {
            try{
                await FestivalAxios.post("/festivali", this.state.festival);
                
                this.props.history.push("/festivali");
            }catch(error) {
                alert("Nije uspelo dobavljanje.");
            }

        }

        addValueInputChange(event) {
            let control = event.target;

            let name = control.name;
            let value = control.value;

            let festival = this.state.festival;
            festival[name] = value;

            this.setState({ festival: festival });
        }

        render(){
            return (
                <div>
                    <h1>Kreiraj festival</h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Naziv festivala</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "naziv"
                            value={this.state.festival.naziv}
                            as="input">
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Datum početka festivala</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "datumPocetka"
                            value={this.state.festival.datumPocetka}
                            as="input">
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Datum završetka festivala</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "datumZavrsetka"
                            value={this.state.festival.datumZavrsetka}
                            as="input">
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Jedinicna cena karte</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "cena"
                            value={this.state.festival.cena}
                            as="input">
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Ukupan broj dostupnih karata</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "dostupneKarte"
                            value={this.state.festival.dostupneKarte}
                            as="input">
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Mesto održavanja festivala</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name= "mestoId"
                            value={this.state.festival.mestoId}
                            as="select">
                                <option value={-1}></option>
                                {this.state.mesta.map((mesto) => {
                                    return(
                                        <option value={mesto.id} key={mesto.id}>
                                            {mesto.grad},{mesto.drzava}
                                        </option>
                                    )
                                })};
                            </Form.Control>
                        </Form.Group>
                        <Button variant="success" onClick={() => this.doAdd()}>
                            Kreiraj festival
                        </Button>
                    </Form>
                </div>
            );
        }
    }

    export default AddFestival;
