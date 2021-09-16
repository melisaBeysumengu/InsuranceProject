import React, { useState } from 'react'
import { Form, Input, Button, Row, Col, Card } from "antd";
import { useHistory } from "react-router-dom";
import OfferService from '../services/OfferService';
import ListOriginalOffers from './ListOriginalOffers';

const CreateOffer = () => {

    const history = useHistory();
    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);
        const response = await OfferService.createOffer(credentials)
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
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value
        });
    };

    return (
        <div>
            <h1>Teklif Oluştur</h1>
            <div>
                <Row type="flex" justify="center" align="middle" >
                    <Col>
                        <Card>
                            <Form
                                name="basic"
                                initialValues={{
                                    remember: true
                                }}
                                onFinish={onFinish}
                                onFinishFailed={onFinishFailed}
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
                                    label="Şirket"
                                    name="provider"
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen şirket ismi girin!"
                                        }
                                    ]}
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
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen kategori girin!"
                                        }
                                    ]}
                                >
                                    <Input
                                        onChange={handleChange}
                                        name="category"
                                        value={credentials.category}
                                    />
                                </Form.Item>
                                
                                <Form.Item
                                    label="Fiyat"
                                    name="price"
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen fiyat girin!"
                                        }
                                    ]}
                                >
                                    <Input
                                        onChange={handleChange}
                                        name="price"
                                        value={credentials.price}
                                    />
                                </Form.Item>

                                <Form.Item
                                    label="Araç Yaş Sınırı (max)"
                                    name="ageLimit"
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen araç yaş sınırı girin!"
                                        }
                                    ]}
                                >
                                    <Input
                                        onChange={handleChange}
                                        name="ageLimit"
                                        value={credentials.ageLimit}
                                    />
                                </Form.Item>

                                <Form.Item
                                    label="Araç Kilometre Sınırı (max)"
                                    name="kilometerLimit"
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen araç kilometre sınırı girin!"
                                        }
                                    ]}
                                >
                                    <Input
                                        onChange={handleChange}
                                        name="kilometerLimit"
                                        value={credentials.kilometerLimit}
                                    />
                                </Form.Item>

                                <Form.Item
                                    label="İndirim (%)"
                                    name="discount"
                                    rules={[
                                        {
                                            required: true,
                                            message: "Lütfen indirim yüzdesi girin!"
                                        }
                                    ]}
                                >
                                    <Input
                                        onChange={handleChange}
                                        name="discount"
                                        value={credentials.discount}
                                    />
                                </Form.Item>

                                <Form.Item
                                    wrapperCol={{
                                        offset: 8,
                                        span: 16
                                    }}
                                >
                                    <Button style={{ margin: "20 auto", width: 100 }} type="primary" htmlType="submit">
                                        KAYDET
                                    </Button>
                                </Form.Item>
                            </Form>
                        </Card>
                    </Col>
                </Row>
                <ListOriginalOffers/>
            </div>
        </div>
    )

}

export default CreateOffer;