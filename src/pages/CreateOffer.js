import React, { useState } from 'react'
import { Form, Input, Button } from "antd";
import { useHistory } from "react-router-dom";

const CreateOffer = () => {

    const history = useHistory();
    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);
        //const response = await AuthService.signin(credentials);
        // if (response) {
        //     history.push("/welcome");
        // } else {
        //     alert("Something went wrong! Try again.")
        // }
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
            <h1>Create Offer</h1>
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
                        label="Başlık"
                        name="title"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen başlık girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="title"
                            value={credentials.title}
                        />
                    </Form.Item>

                    <Form.Item
                        label="İçerik"
                        name="content"
                    >
                        <Input
                            onChange={handleChange}
                            name="content"
                            value={credentials.content}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Fiyat"
                        name="price"
                    >
                        <Input
                            onChange={handleChange}
                            name="price"
                            value={credentials.price}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Şirket"
                        name="provider"
                    >
                        <Input
                            onChange={handleChange}
                            name="provider"
                            value={credentials.provider}
                        />
                    </Form.Item>

                    <Form.Item
                        label="Kategori"
                        name="category"
                    >
                        <Input
                            onChange={handleChange}
                            name="category"
                            value={credentials.category}
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

export default CreateOffer;