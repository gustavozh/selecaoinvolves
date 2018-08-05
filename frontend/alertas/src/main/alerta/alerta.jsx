import React from 'react'

export default props => (
    <div>
        <div className={'card text-white bg-' + props.alertaStyle + ' mb-3'} style={{margin: 20+'px'}}>
            <div className="card-title">
                {props.descricao}
            </div>
            <div className="card-body">
                <table className="table table-borderless card-text" style={{backgroundColor: "rgba(0,0,0,0)"}}>
                    <tbody>
                        <tr>
                            <td>Ponto de Venda: {props.pontoDeVenda}</td>
                            <td style={{borderLeft: 4+'px', borderLeftColor: 'gray', borderLeftStyle: 'solid'}}>
                                Data: {props.data}</td>
                        </tr>
                        <tr style={{borderTop: 4+'px', borderTopColor: 'gray', borderTopStyle: 'solid'}}>
                            <td>Objeto: {props.objetoDeAnalise}</td>
                            <td style={{borderLeft: 4+'px', borderLeftColor: 'gray', borderLeftStyle: 'solid'}}>
                                Resultado: {props.resultado === null ? props.descricao : props.resultado}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
)