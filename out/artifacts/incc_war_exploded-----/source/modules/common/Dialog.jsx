import React from 'react';
import ReactDOM from 'react-dom';
import {connect} from 'react-redux';
import { Modal, Button } from 'antd';
import {showModalDialog} from 'actions/CommonAction';


/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 下午3:26.
 */

class Dialog  extends React.Component {
    // getInitialState() {
    //     return { visible: false };
    // },
    constructor(props) {
        super(props);
    }

    showModal(dialogConfig) {
        // this.setState({
        //     visible: true,
        // });
        this.props.dispatch(showModalDialog(dialogConfig));
    }
    __handleOk() {
        // this.setState({
        //     visible: false,
        // });
        const {modalDialog} = this.props;
        if(modalDialog.okHandler){
            modalDialog.okHandler();
        }
        this.props.dispatch(showModalDialog({visible: false}));
    }
    __handleCancel(e) {
        // this.setState({
        //     visible: false,
        // });
        const {modalDialog} = this.props;
        if(modalDialog.cancelHandler){
            modalDialog.cancelHandler();
        }
        this.props.dispatch(showModalDialog({visible: false}));
    }
    __handleClose(){
        const {modalDialog} = this.props;
        this.props.dispatch(showModalDialog({visible: false}));
    }
    render() {
        const {modalDialog} = this.props;
        return (
            <div>

                <Modal title={modalDialog.title || "提示信息"}
                       visible={modalDialog.visible}
                       onClose={()=>this.__handleClose()}
                       onOk={()=>this.__handleOk()}
                       onCancel={()=>this.__handleCancel()}
                       okText={modalDialog.okText || "确定"}
                       cancelText={modalDialog.cancelText || "取消"}
                >

                    {modalDialog.content  }
                </Modal>
            </div>
        );
    }
};


export default connect(
    state => {
        let {modalDialog} = state;
        return {
            modalDialog
        }
    }
)(Dialog);