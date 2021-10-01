import { Button, Form } from "antd";
import axios from "axios";
import MaterialTable from 'material-table';
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { Alert, AlertTitle } from '@material-ui/lab';

const ListHouse = (props) => {

    const history = useHistory();

    const [house, setHouse] = useState([]);
    const [selectedRow, setSelectedRow] = useState(false);
    const [select, setSelect] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    const [isSuccesfull, setIsSuccesfull] = useState(false);
    const [successMessages, setSuccessMessages] = useState([]);

    let columns = [
        { title: 'ID', field: 'id', editable: 'never' },
        { title: 'Tehlikeli Bölgede mi?', field: 'isInDangerZone', lookup: { true: 'Evet', false: 'Hayır' }, },
        { title: 'Evin Değeri', field: 'value' },
        { title: 'Bina İnşa Tarzı', field: 'binaInsaTarzi' },
        { title: 'Adres', field: 'address' },
        { title: 'Oda Sayısı', field: 'bedrooms' },
        { title: 'Yüzölçümü', field: 'area' },
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/person/house/${props.match.params.tc}`)
            .then(res => {
                const houses = res.data;
                setHouse(houses);
            })
    }, [])

    const onFinish = async (values) => {
        if (select !== null) {
            history.push(`/show-details/house/${select}`)
        }
        else {
            alert("Araçlardan birini seçmelisin.")
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    const handleRowAdd = (newData, resolve) => {
        //validating the data inputs
        let errorList = []
        console.log(newData)
        if (newData.isInDangerZone == null) {
            errorList.push("Tehlikeli Bölgede mi? alanı boş bırakılamaz")
        }
        if (newData.value == null) {
            errorList.push("Evin Değeri alanı boş bırakılamaz")
        }
        if (newData.binaInsaTarzi == null) {
            errorList.push("Bina İnşa Tarzı alanı boş bırakılamaz")
        }
        if (newData.address == null) {
            errorList.push("Adres alanı boş bırakılamaz")
        }
        if (newData.bedrooms == null) {
            errorList.push("Oda Sayısı alanı boş bırakılamaz")
        }
        if (newData.area == null) {
            errorList.push("Yüzölçümü alanı boş bırakılamaz")
        }

        if (errorList.length < 1) {
            axios.put(`http://localhost:8080/person/house/${props.match.params.tc}`, newData)
                .then(response => {
                    let newHouseData = [...house];
                    newHouseData.push(newData);
                    setHouse(newHouseData);
                    setErrorMessages([])
                    setIserror(false)
                    setIsSuccesfull(true)
                    setSuccessMessages([response.data.message])
                    resolve()
                })
                .catch(error => {
                    setErrorMessages([error.response.data.message])
                    setErrorMessages(["Cannot add data. Server error!"])
                    setIserror(true)
                    setIsSuccesfull(false)
                    setSuccessMessages([])
                    resolve()
                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            setIsSuccesfull(false)
            setSuccessMessages([])
            resolve()
        }
    }

    const handleRowUpdate = (newData, oldData, resolve) => {
        //validating the data inputs
        let errorList = []
        if (newData.isInDangerZone == null) {
            errorList.push("Tehlikeli Bölgede mi? alanı boş bırakılamaz")
        }
        if (newData.value == null) {
            errorList.push("Evin Değeri alanı boş bırakılamaz")
        }
        if (newData.binaInsaTarzi == null) {
            errorList.push("Bina İnşa Tarzı alanı boş bırakılamaz")
        }
        if (newData.address == null) {
            errorList.push("Adres alanı boş bırakılamaz")
        }
        if (newData.bedrooms == null) {
            errorList.push("Oda Sayısı alanı boş bırakılamaz")
        }
        if (newData.area == null) {
            errorList.push("Yüzölçümü alanı boş bırakılamaz")
        }

        if (errorList.length < 1) {
            axios.put(`http://localhost:8080/house/`, newData)
                .then(response => {
                    const updateHouse = [...house];
                    const index = oldData.tableData.id;
                    updateHouse[index] = newData;
                    setHouse([...updateHouse]);
                    resolve()
                    setIserror(false)
                    setErrorMessages([])
                    setIsSuccesfull(true)
                    setSuccessMessages([response.data.message])
                })
                .catch(error => {
                    setErrorMessages(["Güncelleme başarısız. Sunucuda sorun var!"])
                    setIserror(true)
                    setIsSuccesfull(false)
                    setSuccessMessages([])
                    resolve()

                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            setIsSuccesfull(false)
            setSuccessMessages([])
            resolve()
        }
    }

    const handleRowDelete = (oldData, resolve) => {
        axios.delete(`http://localhost:8080/house/${oldData.id}`)
            .then(response => {
                const dataDelete = [...house];
                const index = oldData.tableData.id;
                dataDelete.splice(index, 1);
                setHouse([...dataDelete]);
                resolve()
                setIsSuccesfull(true)
                setSuccessMessages([response.data.message])
            })
            .catch(error => {
                setErrorMessages(["Silme başarısız. Sunucuda sorun var!"])
                setIserror(true)
                setIsSuccesfull(false)
                setSuccessMessages([])
                resolve()
            })
    }

    return (
        <div className="app">
            <MaterialTable
                title="Ev Bilgileri"
                columns={columns}
                data={house}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1,
                    search: false,
                    rowStyle: rowData => ({
                        backgroundColor: (selectedRow === rowData.tableData.id) ? '#7cabe1' : '#FFF'
                    })
                }}
                onRowClick={((evt, selectedRow) => {
                    setSelectedRow(selectedRow.tableData.id)
                    setSelect(selectedRow.id)
                    console.log("selected row",selectedRow.id)
                })}
                editable={{
                    onRowUpdate: (newData, oldData) =>
                        new Promise((resolve) => {
                            handleRowUpdate(newData, oldData, resolve);
                        }),
                    onRowAdd: (newData) =>
                        new Promise((resolve) => {
                            handleRowAdd(newData, resolve)
                        }),
                    onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                            handleRowDelete(oldData, resolve)
                        }),
                }}
            />
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
                        Teklifleri Al
                    </Button>
                </Form.Item>


            </Form>
        </div>
    );
}

export default ListHouse;