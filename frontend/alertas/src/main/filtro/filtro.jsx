import React from 'react'

export default props => {
    const pontoDeVendaItem = () =>{
        var key = 1
        const list = props.pontosDeVenda || []
        return list.map(pontoDeVenda => (
            <a key={key++} className="dropdown-item" 
                onClick={() => props.select(pontoDeVenda, null)}>{pontoDeVenda}</a>
        ))
    }
    
    const tipoItem = () =>{
        var key = 1
        const list = props.tipos || []
        return list.map(tipo => (
            <a key={key++} className="dropdown-item" 
                onClick={() => props.select(null, tipo)}>{tipo}</a>
        ))
    }

    return (
        <div className="row justify-content-left align-items-center">
            <div className="col-1"><h2>Filtros: </h2></div>
            <div className="col-1 btn-group">
                <div className="dropdown">
                    <button className="btn btn-default dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Ponto de Venda
                    </button>
                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        {pontoDeVendaItem()}
                    </div>
                </div>
                <div className="dropdown">
                    <button className="btn btn-default dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Tipo
                    </button>
                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        {tipoItem()}
                    </div>
                </div>
                <button className="btn btn-danger" onClick={props.search}>Limpar Filtros</button>
            </div>
        </div>
    )
}