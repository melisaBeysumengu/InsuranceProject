import axios from "axios";
import React, { useEffect, useState } from 'react';
import MaterialTable from 'material-table';
import localization from "../globals/MaterialTableConstants";

export default function ListInsuredHouses() {

    const [dask, setDask] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/kasko/all`)
            .then(res => {
                const offers = res.data;
                setDask(offers);
            })
    }, [])

    let columns = [
        { title: 'TC Kimlik Numarası', field: 'person.tcNumber', editable: 'onAdd' },
        { title: 'Ad', field: 'person.name' },
        { title: 'Soyad', field: 'person.surname' },
        { title: 'Adres', field: 'person.address' },
        { title: 'Gelir', field: 'person.income' },
        { title: 'Plaka', field: 'vehicle.plateNumber' },
        { title: 'Şasi No', field: 'vehicle.chassisNumber' },
        { title: 'Renk', field: 'vehicle.color' },
        { title: 'Araç Yaşı', field: 'vehicle.age' },
        { title: 'Araç Kilometresi', field: 'vehicle.kilometer' },
        { title: 'Başlık', field: 'kasko.title' },
        { title: 'Oluşturulma Tarihi', field: 'kasko.createdAt' },
        { title: 'Sigorta Şirketi', field: 'kasko.provider' },
        { title: 'Fiyat', field: 'kasko.price' },
        { title: 'İçerik', field: 'kasko.content' },
    ]

    return (
        <div>
            <MaterialTable
                title="Kasko Yaptırılan Araç Detayları"
                columns={columns}
                data={dask}
                localization={localization}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    grouping: true
                }}
            />
        </div>
    )
}
