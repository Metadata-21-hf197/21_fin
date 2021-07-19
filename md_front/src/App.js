import './App.css';
import React from 'react';
import Main from './main.js';
import Login from './login.js';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';

function App() {
  
  return (
    <Login/> 
  );
}
//<Route path='/' component={Login}/>
export default App;
