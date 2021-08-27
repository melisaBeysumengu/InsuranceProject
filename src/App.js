import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "antd/dist/antd.css";
import "../src/styles.css";
import { Layout, Menu, Breadcrumb } from "antd";
import Home from "./pages/Home";
import PersonDetails from "./pages/PersonDetails";
import VehicleDetails from "./pages/VehicleDetails";
import CreateOffer from "./pages/CreateOffer";
import MySteps from "./pages/MySteps";

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
                <span>Anasayfa</span>
                <Link to="/" />
              </Menu.Item>
              <Menu.Item key="2">
                <span>Teklif Al</span>
                <Link to="/my-steps" />
              </Menu.Item>
              <Menu.Item key="3">
                <span>Teklif Yarat</span>
                <Link to="/create-offer" />
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
              <Route path="/person-details" component={PersonDetails} />
              <Route path="/vehicle-details" component={VehicleDetails} />
              <Route path="/create-offer" component={CreateOffer} />
              <Route path="/my-steps" component={MySteps} />
            </Content>
          </Layout>
        </Layout>
      </Router>
    );
  }
}

export default App;