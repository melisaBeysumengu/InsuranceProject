import { Card, Space } from "antd";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";

const ListOriginalOffers = () => {

    const [kasko, setKasko] = useState([]);
    const [offer, setOffer] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/kasko/`)
            .then(res => {
                const offers = res.data;
                setKasko(offers);
                console.log(kasko)
            })
        axios.get(`http://localhost:8080/dask/`)
            .then(res => {
                const offers = res.data;
                setOffer(offers);

                console.log(offer)
            })
    }, [])

    return (
        <div style={{ margin: '0 auto', width: '50%' }}>
            <Space direction="horizontal">
                <Link to="/list-person/vehicle">
                    <Card
                        title={kasko.title}
                        hoverable={true}
                        extra={
                            <Space direction="vertical">
                                <Link to="/list-person/vehicle">Teklif Al</Link>
                            </Space>
                        }>
                        <p>Ücret (sabit): {kasko.price}</p>
                        <p>İndirim: {kasko.discount}</p>
                        <p>Kilometre Sınırı (max): {kasko.kilometerLimit}</p>
                        <p>Sigorta Şirketi: {kasko.provider}</p>
                    </Card>
                </Link>
                <Link to="/list-person/house">
                    <Card
                        title={offer.title}
                        hoverable={true}
                        extra={
                            <Space direction="vertical">
                                <Link to="/list-person/house">Teklif Al</Link>
                            </Space>
                        }>
                        <p>Ücret (sabit): {offer.price}</p>
                        <p>İndirim: {offer.discount}</p>
                        <p>Ev Yaş Sınırı (max): {offer.houseAgeLimit}</p>
                        <p>Sigorta Şirketi: {offer.provider}</p>
                    </Card>
                </Link>
            </Space>
        </div>
    );
}

export default ListOriginalOffers;