import React from 'react';
import ReactDOM from 'react-dom';
import {Modal,ModalTrigger,Button} from 'amazeui-react';
import UploadControl from './UploadZoomControl';
import Network from 'tigerfacejs-network';
/**
 * User: zyj
 * Date: 16/4/19.
 * Time: 上午12:09.
 */

export default class UploadModual extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            disabled: false
        };

        //console.log("NaN?", parseInt(this.props.targetWidth || 320), parseInt(this.props.targetHeight || 320));


        this.context = {
            cdn: this.props.cdn,
            system: this.props.system,
            module: this.props.module,
            targetWidth: parseInt(this.props.targetWidth || 320),
            targetHeight: parseInt(this.props.targetHeight || 320),
            note: this.props.note,
            id: this.props.sid
        };

        this._modal = (
            <Modal className="UploadModal" title="图片上传">
                <UploadControl onUploadStart={()=>this.onUploadStart()}
                               isDisabled={()=>{return this.state.disabled;}}
                               context={this.context}/>
            </Modal>
        );

        this.onUploadSuccess = function onUploadSuccess(e) {
            this.onUploadEnd();
            this.refs.trigger && this.refs.trigger.close();
        }.bind(this);

        this.onUploadFailed = function onUploadFailed(e) {
            alert("图片上传失败");
            this.onUploadEnd();
        }.bind(this);
    }

    componentWillUnmount() {
        E.removeEventListener(Network.Event.UPLOAD_SUCCESS, this.onUploadSuccess);
        E.removeEventListener(Network.Event.UPLOAD_FAILED, this.onUploadFailed);
    }

    close() {
        this.setState({showModal: false});
        E.removeEventListener(Network.Event.UPLOAD_SUCCESS, this.onUploadSuccess);
        E.removeEventListener(Network.Event.UPLOAD_FAILED, this.onUploadFailed);
    }

    open() {
        this.setState({showModal: true});
        E.addEventListener(Network.Event.UPLOAD_SUCCESS, this.onUploadSuccess);
        E.addEventListener(Network.Event.UPLOAD_FAILED, this.onUploadFailed);
    }

    onUploadStart() {
        this.setState({disabled: true});
    }

    onUploadEnd() {
        this.setState({disabled: false});
    }

    render() {

        return (
            <div onClick={()=>this.open()}>
                <ModalTrigger
                    ref="trigger"
                    modal={this._modal}
                    show={this.state.showModal}
                    onClose={()=>this.close()}
                />
                {this.props.children}
            </div>
        );
    }
}