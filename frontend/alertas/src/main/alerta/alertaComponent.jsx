import React, { Component } from 'react'
import axios from 'axios'

import Filtro from '../filtro/filtro'
import AlertaList from './alertaList'

const URL = "http://localhost:8080/alertas"

class AlertaComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {list: []}

        this.select = this.select.bind(this)
        this.search = this.search.bind(this)
        
        this.process()
    }

    render() { 
        return (
            <div>
                <Filtro pontosDeVenda={this.state.pontosDeVenda} tipos={this.state.tipos} 
                    select={this.select} search={this.search}/>
                <AlertaList list={this.state.list} />
            </div>
        );
    }

    select(pontoDeVenda, tipo) {
        var list = []
        if (pontoDeVenda){
            this.state.list.forEach(item => {
                if (item.pontoDeVenda === pontoDeVenda){
                    list.push(item);
                }
            })
            this.setState({...this.state, list: list})
        }
        if (tipo){
            this.state.list.forEach(item => {
                if (item.tipo === tipo){
                    list.push(item);
                }
            })
            this.setState({...this.state, list: list})
        }
    }

    process() {
        axios.get(`${URL}/processar`)
            .then(resp => {
                this.search()
            })
            .catch(error => {
                console.log(error)
            })
    }

    search(){
        axios.get(`${URL}`)
            .then(resp => {
                var tipos = []
                var pontosDeVenda = []
                resp.data.forEach(item => {
                    if (pontosDeVenda.indexOf(item.pontoDeVenda) === -1){
                        pontosDeVenda.push(item.pontoDeVenda)
                    }
                    if (tipos.indexOf(item.tipo) === -1){
                        tipos.push(item.tipo)
                    }
                })
                this.setState({...this.state, list: resp.data, pontosDeVenda: pontosDeVenda, tipos: tipos})
            })
            .catch(error => {
                console.log(error)
            })
    }
}
 
export default AlertaComponent;