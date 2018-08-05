import React, { Component } from 'react';
import AlertaComponent from './main/alerta/alertaComponent'
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/bootstrap/dist/js/bootstrap.bundle'


class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Alertas</h1>
        </header>
        <AlertaComponent />
      </div>
    );
  }
}

export default App;
