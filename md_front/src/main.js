import Navbar from './components/navbar'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Word from './pages/Word';
import Home from './pages/Home';
import Terms from './pages/Terms';
import DomainCode from './pages/DomainCode';

function Main() {
  return (
    <Router>
      <Navbar />
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/Word" component={Word} />
        <Route path="/Terms" component={Terms} />
        <Route path="/DomainCode" component={DomainCode} />
      </Switch>
    </Router>
  );
}
export default Main;