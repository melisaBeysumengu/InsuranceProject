import { Layout, Menu } from "antd";
import "antd/dist/antd.css";
import React, { Component } from "react";
import { BrowserRouter as Router, Link, Route } from "react-router-dom";
import "../src/styles.css";
import CreateOffer from "./pages/CreateOffer";
import ListHouse from "./pages/ListHouse";
import ShowDetails from "./pages/ShowDetails";
import ListPerson from "./pages/ListPerson";
import ListVehicle from "./pages/ListVehicle";
import ListInsuredHouses from "./pages/ListInsuredHouses";
import ListInsuredVehicles from "./pages/ListInsuredVehicles";
import ListOriginalOffers from "./pages/ListOriginalOffers";

const { Header, Content, Footer , Sider} = Layout;

class App extends Component {

  state = {
    collapsed: false
  };

  onCollapse = (collapsed) => {
    this.setState({ collapsed });
  };
  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed
    });
  };

  render() {
    return (
      <Router>
        <Layout style={{ minHeight: "100vh" }}>
          <Sider
            collapsible
            collapsed={this.state.collapsed}
            onCollapse={this.onCollapse}
          >
            <div className="logo" />
            <Menu theme="dark" defaultSelectedKeys={["1"]} mode="inline">
              <Menu.Item key="1">
                <span>Teklifler</span>
                <Link to="/" />
              </Menu.Item>
              <Menu.Item key="2">
                <span>Sigortalı Araçlar</span>
                <Link to="/list-insured-vehicles" />
              </Menu.Item>
              <Menu.Item key="3">
                <span>Sigortalı Evler</span>
                <Link to="/list-insured-houses" />
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout>
            <Content
              style={{
                margin: "24px 16px",
                padding: 24,
                background: "#fff",
                minHeight: 280
              }}
            >
              <Route path="/" component={ListOriginalOffers} exact />
              <Route path="/list-vehicle/:tc" component={ListVehicle} />
              <Route path="/list-house/:tc" component={ListHouse} />
              <Route path="/show-details/:type/:id" component={ShowDetails} />
              <Route path="/list-person/:type" component={ListPerson} />
              <Route path="/list-insured-houses" component={ListInsuredHouses} />
              <Route path="/list-insured-vehicles" component={ListInsuredVehicles} />
            </Content>
          </Layout>
        </Layout>
      </Router>
    );
  }
}

export default App;
