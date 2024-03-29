import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, HashRouter } from 'react-router-dom';
import { CookiesProvider } from 'react-cookie';

import App from './App';
import reportWebVitals from './reportWebVitals';
import AuthProvider from './store/AuthProvider';
import UserProvider from './store/UserProvider';

ReactDOM.render(
  <React.StrictMode>
    <CookiesProvider>
      <AuthProvider>
        <UserProvider>
        <HashRouter hashType='noslash'>
          <App />
        </HashRouter>
        </UserProvider>
      </AuthProvider>
    </CookiesProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
