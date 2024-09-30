import * as React from 'react';
import { CssVarsProvider } from '@mui/joy/styles';
import CssBaseline from '@mui/joy/CssBaseline';
import Box from '@mui/joy/Box';
import Button from '@mui/joy/Button';
import Breadcrumbs from '@mui/joy/Breadcrumbs';
import Link from '@mui/joy/Link';
import Typography from '@mui/joy/Typography';
import { routes, anonymousRoutes } from './config/routes';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import ChevronRightRoundedIcon from '@mui/icons-material/ChevronRightRounded';
import DownloadRoundedIcon from '@mui/icons-material/DownloadRounded';
import Sidebar from './components/Sidebar';
import OrderTable from './components/ExpenseTable';
import OrderList from './components/OrderList';
import Header from './components/Header';
import RoutesComponent from "./utils/RoutesComponent";

export default function App() {
  return (
    <CssVarsProvider disableTransitionOnChange>
      <CssBaseline />
      <RoutesComponent routes={routes} anonymousRoutes={anonymousRoutes} />
    </CssVarsProvider>
  );
}
