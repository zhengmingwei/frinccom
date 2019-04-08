import React from "react";
import {Form, Button, Icon, Upload} from "antd";
import ReactDOM from 'react-dom';
const createForm = Form.create;

function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
}

function beforeUpload(file) {
    const isJPG = file.type === 'video/mp4' || file.type === 'video/avi' || file.type === 'video/flv' ;
    if (!isJPG) {
        alert('只能传 mp4/avi/flv 文件!');
    }
    return isJPG;
}
//noinspection JSAnnotator
class VideoUpload extends React.Component {

    constructor(){
        super();
        this.state = {
            VideoUrl:null
        }
    };
    handleChange = (info) => {
        if (info.file.status === 'done') {
        	 this.state = {
                 VideoUrl:null
             }
            getBase64(info.file.originFileObj, VideoUrl => this.setState({ VideoUrl}));
            this.triggerChange(info);
        }
        };
    triggerChange = (info) => {
        const onChange = this.props.onChange;
        if (onChange) {
            const {file} = info;
            const s = JSON.stringify(file.response.result);
            console.log("s=>", s);
            onChange(s);
        }
    };
    handleRemove = (file) =>{
        this.setState({VideoUrl:null});
    };

    _setIntiValue = () =>{
        const {initialValue} = this.props;
        if (initialValue){
            const initObj = eval("("+initialValue+")");
            this.fileList = [{"uid": initObj.uid, "name":"", "status": "done", "response":{"status": "success", "result":initObj}} ];
            if (initObj){
                this.state.VideoUrl = initObj.url ;
                // console.log("_setIntiValue", this.fileList);
                 this.triggerChange({file:this.fileList[0]})
            }
        } else {
          
        }
    }

    shouldComponentUpdate(nextProps, nextState) {

        return true;
    }
    componentDidMount() {

        this._setIntiValue();
    }

    render() {
       
        const VideoUrl = this.state.VideoUrl;
        return (
            <Upload name="uploadFile" action={this.props.action}  beforeUpload={beforeUpload}
                    onChange={this.handleChange} className="avatar-uploader" showUploadList={false} >
                {
                    VideoUrl ?
                       <video width="148" height="150" controls="controls">
                            <source src={VideoUrl} type="video/mp4"/>
                        </video>
                        :<Icon type="plus" className="avatar-uploader-trigger" />
                }
            </Upload>
        );
    }
}
VideoUpload = createForm()(VideoUpload);
export default VideoUpload;