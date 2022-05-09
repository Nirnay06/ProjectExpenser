import { useContext } from "react";
import { Route, Switch, Redirect } from "react-router-dom";
import AuthContext from "../store/AuthContext";
import { routes, anonymousRoutes } from './../config/routes';


export const RouteWithSubRoutes = (route) => {
    const auth = useContext(AuthContext);
    return (
        <Route path={route.path} exact={route.exact} render={({ location }) => auth.isLoggedIn ?
            (route.children) :
            (<Redirect to={{
                pathname: "/login",
                state: { from: location }
            }} />)
        }>

        </Route>
    );
}
const RoutesComponent = (props) => {
    return (
        <Switch>
            {anonymousRoutes.map((route) => {
                return <Route key={route.path} path={route.path} exact={route.exact}>
                    <route.layout>
                        <route.component />
                    </route.layout>
                </Route>
            })}

            {routes.map((route) => {

                return <RouteWithSubRoutes key={route.path} {...route} >
                    {!route.redirect &&
                        <route.layout>
                            <route.component routes={route.routes} />
                        </route.layout>
                    }
                    {route.redirect && <Redirect to={route.redirect} />}
                </RouteWithSubRoutes>
            })}
        </Switch >)
}

export default RoutesComponent;