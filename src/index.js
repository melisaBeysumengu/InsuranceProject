import ReactDOM from "react-dom";
import App from "../src/App";
import { BrowserRouter} from "react-router-dom"

//AxiosConfigurer.configure();


const rootElement = document.getElementById("root");
ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  rootElement
);

