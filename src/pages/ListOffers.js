import axios from "axios";
import { useHistory } from "react-router-dom";
import React, { useEffect, useState, useRef } from 'react';
import MaterialTable from 'material-table';
import { Form, Button } from "antd";




export default function ListOffers(props) {

    const [offer, setOffer] = useState([]);
    const [presentOffer, setPresentOffer] = useState([]);
    const [select, setSelect] = useState([]);

    let columns = [
        { title: 'İsim', field: 'title' },
        { title: 'Şirket', field: 'provider' },
        { title: 'İçerik', field: 'content' },
        { title: 'Fiyat', field: 'price' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/offer/category/vehicle`)
            .then(res => {
                const offers = res.data;
                setOffer(offers);
            })

        axios.get(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}`)
            .then(res => {
                const presentOffers = res.data;
                setPresentOffer(presentOffers);
            })

    }, [])

    const onFinish = async () => {
        console.log("on finish: ",select)
        console.log(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}/${select}`)
        const request = await axios.put(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}/${select}`)
            .then((response) => { console.log(response.data) })
            .catch((error) => error);
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    return (
        <div className="app">
            <MaterialTable
                title="Kabul Edilen Teklif Bilgileri"
                columns={columns}
                data={presentOffer}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                }}
            />


            <MaterialTable
                title="Teklif Bilgileri"
                columns={columns}
                data={offer}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                    selection: true
                }}
                onSelectionChange={(data, rowData) => {
                    if (rowData.tableData.checked) {
                        setSelect(rowData.id)
                        console.log("on selection change: ",rowData.id)
                    }
                }}
            />

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
                    wrapperCol={{
                        offset: 8,
                        span: 16
                    }}
                >
                    <Button type="primary" htmlType="submit">
                        EKLE
                    </Button>
                </Form.Item>


            </Form>
        </div>
    );
}
