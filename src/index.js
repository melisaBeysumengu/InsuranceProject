import ReactDOM from "react-dom";
import { StrictMode } from "react";
import App from "../src/App";
import { BrowserRouter, Route, Switch } from "react-router-dom"

//AxiosConfigurer.configure();


const rootElement = document.getElementById("root");
ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  rootElement
);

