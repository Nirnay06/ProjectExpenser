import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import RoutesComponent from './utils/RoutesComponent';
import { routes, anonymousRoutes } from './config/routesCommon';
import { CssBaseline, CssVarsProvider } from '@mui/joy';
function App() {
  return (
    <CssVarsProvider disableTransitionOnChange>
      <CssBaseline /><RoutesComponent routes={routes} anonymousRoutes={anonymousRoutes} />
      </CssVarsProvider>);
}


export default App;
