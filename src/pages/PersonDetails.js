import React, { useState } from 'react'
import { Form, Input, Button, InputNumber } from "antd";
import { useHistory } from "react-router-dom";
import PersonService from '../services/PersonService';

const PersonDetails = () => {

    const history = useHistory();
    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);
        const response = await PersonService.createUser(credentials)
        if (response) {
            console.log(response)
            return response;
        } else {
            return false;
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    const handleChange = (event) => {
        console.log(event.target.value)
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value
        });
    };

    return (
        <div>
        <h1>Kullanıcı Oluştur</h1>
            <div>
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
                        label="İsim"
                        name="name"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen adınızı girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="name"
                            value={credentials.name}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Soyisim"
                        name="surname"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen soyadınızı girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="surname"
                            value={credentials.surname}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Adres"
                        name="address"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen adresinizi girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="address"
                            value={credentials.address}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Gelir"
                        name="income"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen gelirinizi girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="income"
                            value={Number(credentials.income)}
                        />
                    </Form.Item>

                    <Form.Item
                        wrapperCol={{
                            offset: 8,
                            span: 16
                        }}
                    >
                        <Button style={{ margin: "20 auto", width: 100 }} type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>

            </div>
        </div>
    )

}

export default PersonDetails;