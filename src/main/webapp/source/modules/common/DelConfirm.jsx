import React from 'react';
import ReactDOM from 'react-dom';
import { Modal, Button } from 'antd';
const confirm = Modal.confirm;
/**
 * User: zhyj
 * Date: 2017/7/31.
 * Time: 22:38.
 */
export default class DelConfirm extends React.Component {
    constructor() {
        super(...arguments);
    }

    showConfirm() {
        const that = this
        confirm({
            title: '系统提示',
            okText: '确定',
            cancelText: '取消',
            content: '确定要删除吗？',
            onOk() {
                that.props.onOk();
            },
            onCancel() {
            },
        });
    }

    render() {
        const style = this.props.style;
        return (
            <a onClick={()=>this.showConfirm()} style={style}> 删除</a>
        )
    }
};
