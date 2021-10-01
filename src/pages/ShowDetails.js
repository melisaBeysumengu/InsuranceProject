import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { Alert, AlertTitle } from '@material-ui/lab';

import { Descriptions } from 'antd';


export default function ListOffers(props) {

    const [propertyInfo, setPropertyInfo] = useState([]);
    const [personInfo, setPersonInfo] = useState([]);
    const [offerInfo, setOfferInfo] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    const [isSuccesfull, setIsSuccesfull] = useState(false);
    const [successMessages, setSuccessMessages] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/${props.match.params.type}/${props.match.params.id}`)
            .then(async res => {
                const propertyDetail = res.data;
                setPropertyInfo(propertyDetail);
                console.log(propertyDetail)
            })

        axios.get(`http://localhost:8080/${props.match.params.type}/owner/${props.match.params.id}`)
            .then(async res => {
                const ownerDetail = res.data;
                setPersonInfo(ownerDetail);
            })

        axios.put(`http://localhost:8080/${props.match.params.type}/${props.match.params.id}/1`)
            .then((response) => {
                setErrorMessages([])
                setIserror(false)
                setIsSuccesfull(true)
                setSuccessMessages([response.data.message])
                setOfferInfo(response.data.content)
            })
            .catch((error) => {
                console.log(error.response.data.content)
                setOfferInfo(error.response.data.content)
                setErrorMessages([error.response.data.message])
                setIserror(true)
                setIsSuccesfull(false)
                setSuccessMessages([])
            });
    }, [])

    return (

        <div className="card text-center m-3">
            <div>
                {iserror &&
                    <Alert severity="warning" variant="filled">
                        <AlertTitle>UYARI</AlertTitle>
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
                    size="small"
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

                {props.match.params.type==="vehicle" &&

                <Descriptions
                    title="Araç Bilgileri"
                    bordered
                    size="small"
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Şasi Numarası">{propertyInfo.chassisNumber}</Descriptions.Item>
                    <Descriptions.Item label="Araç Yaşı">{propertyInfo.age}</Descriptions.Item>
                    <Descriptions.Item label="Araç Kilometre">{propertyInfo.kilometer}</Descriptions.Item>
                    <Descriptions.Item label="Plaka Numarası">{propertyInfo.plateNumber}</Descriptions.Item>
                    <Descriptions.Item label="Renk">{propertyInfo.color}</Descriptions.Item>
                </Descriptions>
                }

                {props.match.params.type==="house"  &&
                <Descriptions
                    title="Ev Bilgileri"
                    bordered
                    size="small"
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Tehlikeli Bölgede mi?">{propertyInfo.isInDangerZone ? "Evet" : "Hayır"}</Descriptions.Item>
                    <Descriptions.Item label="Evin Değeri">{propertyInfo.value}</Descriptions.Item>
                    <Descriptions.Item label="Bina İnşa Tarzı">{propertyInfo.binaInsaTarzi}</Descriptions.Item>
                    <Descriptions.Item label="Adres">{propertyInfo.address}</Descriptions.Item>
                    <Descriptions.Item label="Oda Sayısı">{propertyInfo.bedrooms}</Descriptions.Item>
                    <Descriptions.Item label="Yüzölçümü">{propertyInfo.area}</Descriptions.Item>
                </Descriptions>
                }
                <br /><br />
                <Descriptions
                    title="Teklif Bilgileri"
                    bordered
                    size="small"
                    column={{ xxl: 4, xl: 3, lg: 3, md: 3, sm: 2, xs: 1 }}
                    labelStyle={{ backgroundColor: "#b2d8d8" }}
                >
                    <Descriptions.Item label="Başlık">{offerInfo.title}</Descriptions.Item>
                    <Descriptions.Item label="Oluşturulma Tarihi">{offerInfo.createdAt}</Descriptions.Item>
                    <Descriptions.Item label="Sigorta Şirketi">{offerInfo.provider}</Descriptions.Item>
                    <Descriptions.Item label="Fiyat">{offerInfo.price} ₺</Descriptions.Item>
                    <Descriptions.Item label="İçerik">{offerInfo.content}</Descriptions.Item>
                </Descriptions>
            </div>
        </div>
    );
}
