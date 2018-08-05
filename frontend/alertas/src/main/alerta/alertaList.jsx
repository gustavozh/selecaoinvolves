import React from 'react'
import Alerta from './alerta'

export default props => {
    const renderRows = () => {
        const list = props.list || []
        var key = 1;
        return list.map(alerta => (
            <tr key={key++}>
                <td>
                    <Alerta pontoDeVenda={alerta.pontoDeVenda} objetoDeAnalise={alerta.objetoDeAnalise}
                        data={new Date().toLocaleDateString()} resultado={alerta.margem} descricao={alerta.descricao}
                        alertaStyle={alerta.tipo === 1 ? "danger" : alerta.tipo === 2 ? "warning" : "info"} />
                </td>
            </tr>
        ))
    }
    
    return (
        <table className="table">
            <tbody>
                {renderRows()}
            </tbody>
        </table>
    )
}