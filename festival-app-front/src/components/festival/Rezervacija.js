import React from "react";
import {Button, Form } from "react-bootstrap";

import FestivalAxios from "../../apis/FestivalAxios";

class Rezervacija extends React.Component {
    constructor(props) {
        super(props);

        let festival = {
            naziv: "",
            datumPocetka: "",
            datumZavrsetka: "",
            cena: 0,
            dostupneKarte: 0,
            mestoId: -1,
        };

        let rezervacija = {
            kupljeneKarte: 0,
            ukupnaCena: 0,
            festivalId: this.props.match.params.id
        };

        this.state = {
            festival: festival,
            rezervacija: rezervacija,
            rezervacije: [],
            festivali: [],
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData(){
        await this.getRezervacije();
        await this.getFestivali();
    }

    async getRezervacije(){
        try{
            let rezervacija = this.state.rezervacija;
            let rezervacijaDTO = {
                kupljeneKarte: rezervacija.kupljeneKarte,
                ukupnaCena: rezervacija.ukupnaCena,
                festivalId: rezervacija.festivalId
            }
            let result = await FestivalAxios.get("/rezervacije", rezervacijaDTO);
            if(result && result.status === 200){
                this.setState({
                    rezervacije: result.data,
                });
            }
        }catch(error) {
            alert("Nije uspelo dobavljanje.");
        }
    }

    async getFestivali(){
        try{
            let festival = this.state.festival;
            let festivalDTO = {
                festival: festival.naziv,
                festival: festival.datumPocetka,
                festival: festival.datumZavrsetka,
                festival: festival.dostupneKarte,
                festival: festival.mestoId
            }
            let result = await FestivalAxios.get("/festivali", festivalDTO);
            if(result && result.status ===200){
                this.setState({
                    festivali: result.data,
                });
            }
        }catch(error){
            alert("Nije uspelo dobavljanje.");
        }
    }

    valueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let rezervacija = this.state.rezervacija;
        rezervacija[name] = value;

        this.setState({ rezervacija: rezervacija });
    }

    async rezervisi() {
        try{
            await FestivalAxios.post(`/festivali/${this.props.match.params.id}/rezervisi`, 
            this.state.rezervacija);
            this.getFestivali(0);
        }catch(error){
            alert("Nije moguce promeniti stanje.");
        }

    }

    render(){
        return(
            <div>
                <h1>Rezervacija</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Unesi broj kupljenih karata</Form.Label>
                        <Form.Control
                        onChange={(event) => this.valueInputChange(event)}
                        name="kupljeneKarte"
                        value={this.state.rezervacija.kupljeneKarte}
                        as="input">
                        </Form.Control>
                    </Form.Group>
                    <Button disabled={this.state.festival.dostupneKarte === 1}
                     variant="success" onClick={() => this.rezervisi()}>
                        Rezervisi
                    </Button>
                </Form>
            </div>
        );
    }
}

export default Rezervacija;