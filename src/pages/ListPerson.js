
import axios from "axios";
import React, { useEffect, useState } from 'react';
import MaterialTable from 'material-table';
import { Alert, AlertTitle } from '@material-ui/lab';

const ListPerson = (props) => {

    const [person, setPerson] = useState([]);

    const [iserror, setIserror] = useState(false);
    const [errorMessages, setErrorMessages] = useState([]);

    const [isSuccesfull, setIsSuccesfull] = useState(false);
    const [successMessages, setSuccessMessages] = useState([]);

    let columns = [
        { title: 'TC Kimlik Numarası', field: 'tcNumber', editable: 'onAdd' },
        { title: 'Ad', field: 'name' },
        { title: 'Soyad', field: 'surname' },
        { title: 'Adres', field: 'address' },
        { title: 'Gelir', field: 'income' }
    ]

    useEffect(() => {
        axios.get(`http://localhost:8080/person/`)
            .then(res => {
                const persons = res.data;
                setPerson(persons);
            })
    }, [])


    //function for updating the existing row details
    const handleRowUpdate = (newData, oldData, resolve) => {
        //validating the data inputs
        let errorList = []
        if (newData.tcNumber == null) {
            errorList.push("TC Kimlik Numarası alanı boş bırakılamaz")
        }
        if (newData.name == null) {
            errorList.push("Ad alanı boş bırakılamaz")
        }
        if (newData.surname == null) {
            errorList.push("Soyad alanı boş bırakılamaz")
        }
        if (newData.address == null) {
            errorList.push("Adres alanı boş bırakılamaz")
        }
        if (newData.income == null) {
            errorList.push("Gelir alanı boş bırakılamaz")
        }

        if (errorList.length < 1) {
            axios.put(`http://localhost:8080/person/`, newData)
                .then(response => {
                    const updatePerson = [...person];
                    const index = oldData.tableData.id;
                    updatePerson[index] = newData;
                    setPerson([...updatePerson]);
                    resolve()
                    setIserror(false)
                    setErrorMessages([])
                    setIsSuccesfull(true)
                    setSuccessMessages([response.data.message])
                })
                .catch(error => {
                    setErrorMessages(["Update failed! Server error"])
                    setIserror(true)
                    resolve()

                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            resolve()
        }
    }


    //function for deleting a row
    const handleRowDelete = (oldData, resolve) => {
        axios.delete(`http://localhost:8080/person/${oldData.tcNumber}`)
            .then(response => {
                const dataDelete = [...person];
                const index = oldData.tableData.id;
                dataDelete.splice(index, 1);
                setPerson([...dataDelete]);
                resolve()
                setIsSuccesfull(true)
                setSuccessMessages([response.data.message])
            })
            .catch(error => {
                setErrorMessages(["Delete failed! Server error"])
                setIserror(true)
                resolve()
            })
    }


    //function for adding a new row to the table
    const handleRowAdd = (newData, resolve) => {
        //validating the data inputs
        let errorList = []
        console.log(newData)
        if (newData.tcNumber == null) {
            errorList.push("TC Kimlik Numarası alanı boş bırakılamaz")
        }
        if (newData.name == null) {
            errorList.push("Ad alanı boş bırakılamaz")
        }
        if (newData.surname == null) {
            errorList.push("Soyad alanı boş bırakılamaz")
        }
        if (newData.address == null) {
            errorList.push("Adres alanı boş bırakılamaz")
        }
        if (newData.income == null) {
            errorList.push("Gelir alanı boş bırakılamaz")
        }

        if (errorList.length < 1) {
            axios.post(`http://localhost:8080/person/`, newData)
                .then(response => {
                    let newUserdata = [...person];
                    newUserdata.push(newData);
                    setPerson(newUserdata);
                    resolve()
                    setErrorMessages([])
                    setIserror(false)
                    setIsSuccesfull(true)
                    setSuccessMessages([response.data.message])
                })
                .catch(error => {
                    setErrorMessages(["Cannot add data. Server error!"])
                    setIserror(true)
                    resolve()
                })
        } else {
            setErrorMessages(errorList)
            setIserror(true)
            resolve()
        }
    }


    return (
        <div className="app">
            <MaterialTable
                title="Kullanıcılar"
                columns={columns}
                data={person}
                localization={{
                    body: {
                        editRow: {
                            deleteText: 'Bu kullanıcıyı silmek istediğine emin misin?',
                            saveTooltip: 'Kaydet',
                            cancelTooltip: 'İptal'
                        },
                        editTooltip: 'Düzenle',
                        addTooltip: 'Ekle',
                        deleteTooltip: 'Sil',
                        emptyDataSourceMessage: 'Gösterilecek kayıt yok'
                    },
                    header: { actions: 'Düzenle/Sil' },
                    toolbar: {
                        searchTooltip: 'Arama',
                        searchPlaceholder: 'Kullanıcı Ara'
                    },
                    pagination: {
                        labelRowsSelect: 'satır',
                        labelDisplayedRows: '{count} satırdan {from}-{to} arası',
                        firstTooltip: 'İlk Sayfa',
                        previousTooltip: 'Önceki Sayfa',
                        nextTooltip: 'Sonraki Sayfa',
                        lastTooltip: 'Son Sayfa'
                    }
                }}
                options={{
                    headerStyle: { borderBottomColor: 'red', borderBottomWidth: '3px', fontFamily: 'verdana' },
                    actionsColumnIndex: -1
                }}
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
        </div>
    );
}

export default ListPerson;