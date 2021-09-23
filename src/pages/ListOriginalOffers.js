import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { Alert, AlertTitle } from '@material-ui/lab';

const ListOriginalOffers = () => {

    const [offer, setOffer] = useState([]);
    const [selectedRow, setSelectedRow] = useState(false);
    const [selectedOfferRow, setSelectedOfferRow] = useState();
    const history = useHistory();

    let columns = [
        { title: 'İsim', field: 'title' },
        { title: 'Şirket', field: 'provider' },
        { title: 'İndirim (%)', field: 'discount' },
        { title: 'Kilometre Limiti (max)', field: 'kilometerLimit' },
        { title: 'Araç Yaş Süresi Limiti (max)', field: 'ageLimit' },
        { title: 'Sürücü Ehliyet Süresi (max)', field: 'driverExperienceLimit' },
        { title: 'İçerik', field: 'content' },
        { title: 'Fiyat', field: 'price' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/offer/`)
            .then(res => {
                const offers = res.data;
                setOffer(offers);
                console.log(offers)
            })
    }, [])

    const onFinish = () => {
        if (selectedOfferRow !== null) {
            if (selectedOfferRow === "vehicle") {
                history.push(`/list-person/${selectedOfferRow}`)
            }
        }
        else {
            alert("Tekliflerden birini seçmelisin.")
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    return (
        <div className="app">
            <MaterialTable
                title="Orijinal Teklifler"
                columns={columns}
                data={offer}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    rowStyle: rowData => ({
                        backgroundColor: (selectedRow === rowData.tableData.id) ? '#7cabe1' : '#FFF'
                    })
                }}
                onRowClick={((evt, selectedRow) => {
                    setSelectedRow(selectedRow.tableData.id)
                    setSelectedOfferRow(selectedRow.category)
                    console.log(selectedRow.category)
                })}
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
                <Form.Item wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
                    wrapperCol={{
                        offset: 8,
                        span: 16
                    }}
                >
                    <Button type="primary" htmlType="submit">
                        Devam
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
}

export default ListOriginalOffers;