import logo from './logo.svg';
import './App.scss';
import axios from "axios";
import { BrowserRouter as Router } from 'react-router-dom';
import HeaderComponent from './components/Header';
import PageContent from './components/PageContent';

function App() {
    return (
        <div>
            <Router>
                <HeaderComponent />
                <PageContent />
            </Router>
        </div>
    );
}

export default App
    ;
