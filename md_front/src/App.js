import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './components/login';
import MainBox from './pages/MainPage';
import Join from './components/join';

function App() {

    return (
      <div>
          <Router>
            <Switch>
              <Route exact path="/" component={Login}/>
              <Route exact path="/join" component={Join}/>
              <Route exact path="/main"  component={MainBox}/>
            </Switch>
          </Router>

      </div>
    );
}

export default App;
