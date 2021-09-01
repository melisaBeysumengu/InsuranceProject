import React, { useState } from 'react'
import { Form, Input, Button, InputNumber } from "antd";
import { useHistory } from "react-router-dom";
import VehicleService from '../services/VehicleService';

const VehicleDetails = () => {

    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);
        const response = await VehicleService.createVehicle(credentials)
        if (response && response.data) {
            console.log(response.data.tcNumber)
            return true;
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
            <h1>Araç Oluştur</h1>
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
                        label="Plaka Numarası"
                        name="plateNumber"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen plaka numaranızı girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="plateNumber"
                            value={credentials.plateNumber}

                        />
                    </Form.Item>

                    <Form.Item
                        label="Şasi Numarası"
                        name="chassisNumber"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen şasi numarası girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="chassisNumber"
                            value={credentials.chassisNumber}

                        />
                    </Form.Item>

                    <Form.Item
                        label="Renk"
                        name="color"
                        rules={[
                            {
                                required: true,
                                message: "Lütfen renk girin!"
                            }
                        ]}
                    >
                        <Input
                            onChange={handleChange}
                            name="color"
                            value={credentials.color}

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

export default VehicleDetails;