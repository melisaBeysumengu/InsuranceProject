import PersonService from "../services/PersonService";
import axios from "axios";
import { useHistory } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import MaterialTable, { MTableFilterRow } from 'material-table';
import { Alert, AlertTitle } from '@material-ui/lab';
import { Form, Input, Button } from "antd";

const ListVehicle = (props) => {

    const [vehicle, setVehicle] = useState([]);
    const [select, setSelect] = useState([]);
    const history = useHistory();

    let columns = [
        { title: 'Plaka', field: 'plateNumber' },
        { title: 'Şasi No', field: 'chassisNumber' },
        { title: 'Renk', field: 'color' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/person/vehicle/${props.match.params.tc}`)
            .then(res => {
                const vehicles = res.data;
                setVehicle(vehicles);
            })
    }, [])

    const onFinish = async (values) => {
        if(select!==null){
            history.push(`/list-offers/${select}`)
        }
        else {
            alert("Araçlardan birini seçmelisin.")
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    return (
        <div className="app">
            <MaterialTable
                title="Araç Bilgileri"
                columns={columns}
                data={vehicle}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                    selection: true
                }}
                onSelectionChange={(data, rowData) => {
                    if (rowData.tableData.checked) {
                        setSelect(rowData.chassisNumber)
                        console.log("on selection change: ",rowData.chassisNumber)
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
                        Teklifleri Gör
                    </Button>
                </Form.Item>


            </Form>
        </div>
    );
}

export default ListVehicle;