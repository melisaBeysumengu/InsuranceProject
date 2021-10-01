import axios from "axios";
import React, { useEffect, useState } from 'react';
import MaterialTable from 'material-table';
import localization from "../globals/MaterialTableConstants";

export default function ListInsuredHouses() {

    const [dask, setDask] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/dask/all`)
            .then(res => {
                const offers = res.data;
                offers.map((value, index) => {
                    if(value.house.isInDangerZone){
                        value.house.isInDangerZone = "Evet";
                    }else{
                        value.house.isInDangerZone = "Hayır";
                    }
                })
                setDask(offers);
            })
    }, [])

    let columns = [
        { title: 'TC Kimlik Numarası', field: 'person.tcNumber', editable: 'onAdd' },
        { title: 'Ad', field: 'person.name' },
        { title: 'Soyad', field: 'person.surname' },
        { title: 'Adres', field: 'person.address' },
        { title: 'Gelir', field: 'person.income' },
        { title: 'Tehlikeli Bölgede mi?', field: 'house.isInDangerZone'},
        { title: 'Evin Değeri', field: 'house.value' },
        { title: 'Bina İnşa Tarzı', field: 'house.binaInsaTarzi' },
        { title: 'Adres', field: 'house.address' },
        { title: 'Oda Sayısı', field: 'house.bedrooms' },
        { title: 'Yüzölçümü', field: 'house.area' },
        { title: 'Başlık', field: 'dask.title' },
        { title: 'Oluşturulma Tarihi', field: 'dask.createdAt' },
        { title: 'Sigorta Şirketi', field: 'dask.provider' },
        { title: 'Fiyat', field: 'dask.price' },
        { title: 'İçerik', field: 'dask.content' },
    ]

    return (
        <div>
            <MaterialTable
                title="Dask Yaptırılan Ev Detayları"
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
