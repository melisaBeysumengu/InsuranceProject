import { Form, Input, Button } from "antd";
import { useHistory } from "react-router-dom";
import { useState } from "react";
import PersonService from '../services/PersonService';

const Home = () => {
  const history = useHistory();
  const [credentials, setCredentials] = useState({});
  const [user, setUser] = useState({});

  const onFinish = async (values) => {
    console.log("Success:", values);
    const response = await PersonService.getUser(credentials)
    if (response) {
        history.push(`/list-vehicle/${credentials.tcNumber}`)
    } else {
        return false;
    }
};

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  const handleChange = (event) => {
    setCredentials({
      ...credentials,
      [event.target.name]: event.target.value
    });
  };

  return (
    <div>
      <h1>Teklif Al</h1>
      <Form
        name="basic"
        labelCol={{
          span: 8
        }}
        wrapperCol={{
          span: 16
        }}
        initialValues={{
          remember: true
        }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        style={{ margin: "0 auto", width: 400 }}
      >
        <Form.Item
          label="TC Kimlik No"
          name="tcNumber"
          rules={[
            {
              required: true,
              message: "Lütfen kimlik numaranızı girin!"
            }
          ]}
        >
          <Input
            type="number"
            onChange={handleChange}
            name="tcNumber"
            value={Number(credentials.tcNumber)}

          />
        </Form.Item>

        <Form.Item
          wrapperCol={{
            offset: 8,
            span: 16
          }}
        >
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>


      </Form>

        <h1>{user ? user.data : ""  }</h1>
    </div>
  );
};

export default Home;
