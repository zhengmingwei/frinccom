import React from "react";
import {Form, Button, Icon, Upload} from "antd";
import ReactDOM from 'react-dom';
const createForm = Form.create;
//noinspection JSAnnotator
export default  class LimitUpload extends React.Component {

    constructor(props) {
        super(props);
    }

    state = {
        fileList: [
        ],
    }

    handleChange = (info) => {
        let fileList = info.fileList;
        console.log("filelist --- ", fileList)
        fileList = fileList.slice(-this.props.maxlength);
        this.setState({fileList});
        if (info.file.status === 'done') {
            this.triggerChange(info);
        }
    }

    triggerChange = (info) => {
        const onChange = this.props.onChange;
        if (onChange) {
            const {file} = info;
            console.log("info",info)
            const s = JSON.stringify(file.response.result);
            console.log("s=>", s);
            onChange(s);
        }
    };

    _setIntiValue = () =>{
        const {initialValue} = this.props;
        if (initialValue){
            const initObj = eval("("+initialValue+")");
            this.state.fileList = [{"uid": initObj.uid, "name":initObj.name, "status": "done", "response":{"status": "success", "result":initialValue}} ];
            // if (initObj)this.state.imageUrl = initObj.url ;
        } else {
            this.state.fileList =[];
            // this.state.fileList = [];
        }
    }
    componentDidMount() {
        this._setIntiValue();
    }
    render() {
        const props = {
            onChange: this.handleChange,
        };
        return (
            <Upload {...props} name = {this.props.name} action={this.props.action} onChange={this.handleChange} fileList={this.state.fileList}>
                {this.props.children}
            </Upload>
        );
    }
};
// LimitUpload = createForm()(LimitUpload);
// export default LimitUpload;
