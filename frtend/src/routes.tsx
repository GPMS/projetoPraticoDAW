import { Route, BrowserRouter, Redirect } from 'react-router-dom';
import User from 'pages/user';
import Admin from 'pages/admin';

const Routes = () => {
  return (
    <BrowserRouter>
      <Route exact path="/" >
        <Redirect to="/user" />
      </Route>
      <Route component={User} path="/user" />
      <Route component={Admin} path="/admin" />
    </BrowserRouter>
  );
}
export default Routes;
