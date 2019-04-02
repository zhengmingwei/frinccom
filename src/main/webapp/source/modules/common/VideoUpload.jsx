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
    //alert(file.type);
    const isJPG =  file.type === 'video/mp4' ;
    if (!isJPG) {
        alert('只能传 video/mp4 文件!');
    }
    const isLt3M = file.size / 1024 / 1024 < 30;

    //alert(isLt3M);
    if (!isLt3M) {
        alert('视频不能大于 30MB!');
    }
    return isJPG && isLt3M;
}
//noinspection JSAnnotator
class VideoUpload extends React.Component {

    constructor(){
        super();
        this.state = {
            imageUrl:null
        }
    // const value = this.props.value || {};

    };
    handleChange1 = (info) => {
        console.log(info);
        if (info.file.status === 'done') {
            this.state = {
                imageUrl:null
            }
            getBase64(info.file.originFileObj, imageUrl => this.setState({ imageUrl }));
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
        this.setState({imageUrl:null});
    };

    _setIntiValue = () =>{
        const {initialValue} = this.props;
        if (initialValue){
            const initObj = eval("("+initialValue+")");
            this.fileList = [{"uid": initObj.uid, "name":"", "status": "done", "response":{"status": "success", "result":initObj}} ];
            if (initObj){
                this.state.imageUrl = initObj.url ;
                // console.log("_setIntiValue", this.fileList);
                 this.triggerChange({file:this.fileList[0]})
            }
        } else {
            // this.fileList =[];
            // this.state.imageUrl = null;
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
//         const nextInit = nextProps.initialValue;
//         const init = this.props.initialValue;
//         if(nextInit != init){
//
//             console.log("nextProps", nextProps)
//             console.log("init", nextProps)
//             this._setIntiValue();
//             return true;        }
//             console.log(nextProps);
// return false;
        return true;
    }
    componentDidMount() {
// console.log("ImgUpload componentDidMount")
        this._setIntiValue();
    }

    render() {
        // if (this.state.imageUrl == null){
        //    this._setIntiValue();
        //     console.log("this.state.imageUrl ", this.state.imageUrl );
        //     console.log("this.fileLIst", this.fileList);
        // }
        const imageUrl = this.state.imageUrl;
        return (
            <Upload name="uploadFile" action={this.props.action}  beforeUpload={beforeUpload}
                    onChange={this.handleChange1} className="avatar-uploader" showUploadList={false} >
                {
                    imageUrl ?
                        <video width="148" height="150" controls="controls">
                           {/* <source src="movie.ogg" type="video/ogg" />*/}
                            <source src={imageUrl} type="video/mp4"></source>
                        </video> :
                        <Icon type="plus" className="avatar-uploader-trigger" />
                }
            </Upload>
        );
    }
}
VideoUpload = createForm()(VideoUpload);
export default VideoUpload;