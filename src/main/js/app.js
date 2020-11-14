import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'
import ListComponent from "./components/ListComponent";
import AddComponent from "./components/AddComponent";
import EditComponent from "./components/EditComponent";
import Header from "./components/Header";
const ReactDOM = require('react-dom');
import LoginView from './Views/LoginView'
import HomeView from './Views/HomeView'
import AboutView from './Views/AboutView'
import WorkWithView from "./Views/WorkWithView";
import RegisterView from "./Views/RegisterView";
import RegisterQuestionsView from "./Views/RegisterQuestionsView";

function App() {

	return (

		<div>

			<Router>
				<Header/>
				<div >

					<Switch>
						<Route path="/" exact><HomeView/></Route>
						<Route path="/list" component={ListComponent} />
						<Route path="/add" component={AddComponent} />
						<Route path="/edit" component={EditComponent} />
						<Route path="/about" component={EditComponent}><AboutView/></Route>
						<Route path="/WorkWith" component={EditComponent}><WorkWithView/></Route>
						<Route path="/login">
							<LoginView/>
						</Route>
						<Route path="/Register">
							<RegisterView/>
						</Route>
						<Route path="/registerQuestions">
							<RegisterQuestionsView/>
						</Route>

					</Switch>
				</div>
			</Router>
		</div>
	);
}


export default App;

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
