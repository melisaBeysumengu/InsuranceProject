import { Layout, Menu } from "antd";
import "antd/dist/antd.css";
import React, { Component } from "react";
import { BrowserRouter as Router, Link, Route } from "react-router-dom";
import "../src/styles.css";
import CreateOffer from "./pages/CreateOffer";
import Home from "./pages/Home";
import ListOffers from "./pages/ListOffers";
import ListPerson from "./pages/ListPerson";
import ListVehicle from "./pages/ListVehicle";
import VehicleDetails from "./pages/VehicleDetails";

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
                <span>Kullanıcılar</span>
                <Link to="/list-person" />
              </Menu.Item>
              <Menu.Item key="2">
                <span>Teklif Yarat</span>
                <Link to="/create-baseOffer" />
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout>
            {/* <Header style={{ background: "#fff", padding: 0, paddingLeft: 16 }}>HEADER
            </Header> */}
            <Content
              style={{
                margin: "24px 16px",
                padding: 24,
                background: "#fff",
                minHeight: 280
              }}
            >
              <Route path="/" component={Home} exact />
              <Route path="/vehicle-details" component={VehicleDetails} />
              <Route path="/create-baseOffer" component={CreateOffer} />
              <Route path="/list-vehicle/:tc" component={ListVehicle} />
              <Route path="/list-offers/:chassisNumber" component={ListOffers} />
              <Route path="/list-person/" component={ListPerson} />
            </Content>
          </Layout>
        </Layout>
      </Router>
    );
  }
}

export default App;
