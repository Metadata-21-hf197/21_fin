import './App.css';
import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './components/login';
import Header from './components/header';
import Terms from './pages/TermsPage';
import Word from './pages/WordPage';
import DomainCode from './pages/DomainCodePage';
import MMS from './pages/MmsPage';
import Join from './components/join';

class App extends Component {
  constructor(props){
    super(props)
    this.state = {

    }
  }

  render() {
    return (
      <div>
        <div>
          <Header/>
          <Router>
            <Switch>
              <Route exact path="/" component={Login}/>
              <Route exact path="/join" component={Join}/>
            </Switch>
          </Router>
            
        </div>

        
        {/*<Router>
          <Switch>
            <Route path="/mms" exact Component={MMS}/>
            <Route path="/domain" Component={DomainCode}/>
            <Route path="/word" Component={Word}/>
            <Route path="/terms" Component={Terms}/>
          </Switch>
        </Router>*/}

      </div>
    )
  }
}

export default App;
