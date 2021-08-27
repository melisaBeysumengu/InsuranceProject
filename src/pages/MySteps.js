import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Steps, Button, message } from 'antd';
import '../styles.css'
import PersonDetails from './PersonDetails';
import ListOffers from './ListOffers';
import VehicleDetails from './VehicleDetails';

const { Step } = Steps;

const steps = [
    {
        title: 'Kişisel Bilgiler',
        content: <PersonDetails/>,
    },
    {
        title: 'Araç Bilgileri',
        content: <VehicleDetails/>,
    },
    {
        title: 'Ev Bilgileri',
        content: 'EV',
    },
    {
        title: 'Teklifler',
        content: <ListOffers/>,
    }
];

const MySteps = () => {
    const [current, setCurrent] = React.useState(0);

    const next = () => {
        setCurrent(current + 1);
    };

    const prev = () => {
        setCurrent(current - 1);
    };

    return (
        <>
            <Steps current={current}>
                {steps.map(item => (
                    <Step key={item.title} title={item.title}/>
                ))}
            </Steps>
            <div className="steps-content">{steps[current].content}</div>
            <div className="steps-action">
                {current < steps.length - 1 && (
                    <Button type="primary" onClick={() => next()}>
                        Next
                    </Button>
                )}
                {current === steps.length - 1 && (
                    <Button type="primary" onClick={() => message.success('Processing complete!')}>
                        Done
                    </Button>
                )}
                {current > 0 && (
                    <Button style={{ margin: '0 8px' }} onClick={() => prev()}>
                        Previous
                    </Button>
                )}
            </div>
        </>
    );
};

export default MySteps;