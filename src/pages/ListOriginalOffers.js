import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { Alert, AlertTitle } from '@material-ui/lab';

const ListOriginalOffers = (props) => {

    const [offer, setOffer] = useState([]);

    let columns = [
        { title: 'İsim', field: 'title' },
        { title: 'Şirket', field: 'provider' },
        { title: 'İçerik', field: 'content' },
        { title: 'Fiyat', field: 'price' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/offer/`)
            .then(res => {
                const offers = res.data;
                setOffer(offers);
            })
    }, [])

    return (
        <div className="app">
            <MaterialTable
                title="Orijinal Teklifler"
                columns={columns}
                data={offer}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1
                }}
            />
        </div>
    );
}

export default ListOriginalOffers;