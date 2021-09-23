import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { Alert, AlertTitle } from '@material-ui/lab';

import { Descriptions } from 'antd';


export default function ListOffers(props) {

    const [vehicleInfo, setVehicleInfo] = useState([]);
    const [personInfo, setPersonInfo] = useState([]);
    const [offerInfo, setOfferInfo] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    const [isSuccesfull, setIsSuccesfull] = useState(false);
    const [successMessages, setSuccessMessages] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}`)
            .then(async res => {
                const vehicleDetail = res.data;
                setVehicleInfo(vehicleDetail);
            })

        axios.get(`http://localhost:8080/vehicle/owner/${props.match.params.chassisNumber}`)
            .then(async res => {
                const ownerDetail = res.data;
                console.log(res.data)
                setPersonInfo(ownerDetail);
            })

        axios.put(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}/1`)
            .then((response) => {
                setErrorMessages([])
                setIserror(false)
                setIsSuccesfull(true)
                setSuccessMessages([response.data.message])
                console.log(response)
                setOfferInfo(response.data.content)
            })
            .catch((error) => {
                setErrorMessages(["Ekleme başarısız. Sunucuda sorun var!"])
                setIserror(true)
                setIsSuccesfull(false)
                setSuccessMessages([])
            });


        // axios.get(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}`)
        //     .then(res => {
        //         const presentOffers = res.data;
        //         setPresentOffer(presentOffers);
        //     })

    }, [])

    // const onFinish = async () => {
    //     console.log("on finish: ", select)
    //     console.log(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}/${select}`)
    //     const request = await axios.put(`http://localhost:8080/vehicle/${props.match.params.chassisNumber}/${select}`)
    //         .then((response) => { console.log(response.data) })
    //         .catch((error) => error);
    // };

    // const onFinishFailed = (errorInfo) => {
    //     console.log("Failed:", errorInfo);
    // };

    return (

        <div className="card text-center m-3">
            <div>
                {iserror &&
                    <Alert severity="error" variant="filled">
                        <AlertTitle>HATA</AlertTitle>
                        {errorMessages.map((msg, i) => {
                            return <div><strong key={i}>{msg}</strong><br /></div>
                        })}
                    </Alert>
                }
                {
                    isSuccesfull &&
                    <Alert severity="success" variant="filled">
                        <AlertTitle>BAŞARILI</AlertTitle>
                        {successMessages.map((msg, i) => {
                            return <strong key={i}>{msg}</strong>
                        })}
                    </Alert>
                }
            </div>
            <br /><br />
            <div className="card-body">
                <Descriptions
                    title="Sürücü Bilgileri"
                    bordered
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Ad">{personInfo.name}</Descriptions.Item>
                    <Descriptions.Item label="Soyad">{personInfo.surname}</Descriptions.Item>
                    <Descriptions.Item label="TC Kimlik Numarası">{personInfo.tcNumber}</Descriptions.Item>
                    <Descriptions.Item label="İkamet">{personInfo.address}</Descriptions.Item>
                    <Descriptions.Item label="Gelir">{personInfo.income}</Descriptions.Item>
                    <Descriptions.Item label="Sürüş Deneyimi">{personInfo.licenceYear}</Descriptions.Item>
                </Descriptions>
                <br /><br />
                <Descriptions
                    title="Araç Bilgileri"
                    bordered
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Şasi Numarası">{vehicleInfo.chassisNumber}</Descriptions.Item>
                    <Descriptions.Item label="Araç Yaşı">{vehicleInfo.age}</Descriptions.Item>
                    <Descriptions.Item label="Araç Kilometre">{vehicleInfo.kilometer}</Descriptions.Item>
                    <Descriptions.Item label="Plaka Numarası">{vehicleInfo.plateNumber}</Descriptions.Item>
                    <Descriptions.Item label="Renk">{vehicleInfo.color}</Descriptions.Item>
                </Descriptions>
                <br /><br />
                <Descriptions
                    title="Teklif Bilgileri"
                    bordered
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Başlık">{offerInfo.title}</Descriptions.Item>
                    <Descriptions.Item label="Fiyat">{offerInfo.price}</Descriptions.Item>
                    <Descriptions.Item label="Sigorta Şirketi">{offerInfo.provider}</Descriptions.Item>
                    <Descriptions.Item label="İçerik">{offerInfo.content}</Descriptions.Item>
                </Descriptions>
            </div>
        </div>
    );
}
