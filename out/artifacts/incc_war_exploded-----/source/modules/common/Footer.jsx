import React from 'react';
import ReactDOM from 'react-dom';
/**
 * User: zyj
 * Date: 16/7/8.
 * Time: 下午3:43.
 */
export default class Footer extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div style={Style}>
                页脚
            </div>
        )
    }
};

const Style = {
    backgroundColor: 'rgba(0, 0, 0, .3)',
    textAlign: 'center',
    padding: '5px',
    marginTop: '5px',
    color: '#FFF'
};