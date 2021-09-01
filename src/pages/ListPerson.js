import PersonService from "../services/PersonService";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import MaterialTable, { MTableFilterRow } from 'material-table';
import { Alert, AlertTitle } from '@material-ui/lab';

const ListPerson = (credentials) => {

    const [product, setProduct] = useState([]);

    const [components, setComponents] = useState({
        FilterRow: props => <MTableFilterRow {...props} />,
    });

    let columns = [
        { title: 'Plaka', field: 'plateNumber' },
        { title: 'Şasi No', field: 'chassisNumber' },
        { title: 'Renk', field: 'color' }
    ]

    useEffect(() => {
        console.log("axx")
        console.log(credentials.match.params.tc)
        axios.get(`http://localhost:8080/person/vehicle/${credentials.match.params.tc}`)
            .then(res => {
                const products = res.data;
                setProduct(products);
                console.log("lhgsldkj")
                console.log(products);
            })
    }, [])

    return (
        <div className="app">
            <MaterialTable
                title="Araç Bilgileri"
                columns={columns}
                data={product}
                components={components}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                    selection: true
                }}
                onSelectionChange={(data, rowData) => {console.log(data)}}
            />
        </div>
    );
}

export default ListPerson;