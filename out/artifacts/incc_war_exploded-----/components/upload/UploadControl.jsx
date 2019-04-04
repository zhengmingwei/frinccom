import React from 'react';
import ReactDOM from 'react-dom';
import {Button,ButtonToolbar,Image} from 'amazeui-react';
import $ from 'jquery';

/**
 * User: zyj
 * Date: 16/4/18.
 * Time: 下午4:26.
 */
export default class UploadControl extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            list: [],
            currentFile: "",
            preview: ""
        };
    }

    uploadFile() {
        let context = this.props.context;
        E.doUpload(context.cdn + context.system + "/" + context.module + "/" + context.id, this.refs.uploadForm);
    }

    selectFile(e) {
        this.refs.uploadFile.click();
    }

    onImageLoaded(e) {
        //E.dispatchEvent("_Upload.ImageLoaded_", e.currentTarget.result);
        this.setState({
            preview: e.currentTarget.result
        });
    }

    onFileSelected(e) {
        if (this.refs.uploadFile.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(this.refs.uploadFile.files[0]);
            reader.onload = (e)=>this.onImageLoaded(e);
        }
    }

    render() {
        //var items = [];
        //this.state.list.forEach(function (item) {
        //    items.push(<UploadItem data={item}/>);
        //}.bind(this));
        var preview = (<Image ref="preview" src={this.state.preview} style={this.state.preview?{}:{display:'none'}}
                              width="200px"/>);
        return (
            <div>
                <div className="UploadViewport" style={{}}>
                    {preview}
                </div>
                <div className="UploadControlBar" style={{padding:10}}>
                    <ButtonToolbar>
                        <Button amStyle="primary" onClick={(e)=>this.selectFile()}>选择</Button>
                        <Button amStyle="danger" onClick={(e)=>this.uploadFile()}>确定</Button>
                    </ButtonToolbar>
                </div>
                <form ref="uploadForm" id="uploadForm" encType="multipart/form-data"
                      style={{visbilty:'hidden',display:'none'}}>
                    <input ref="uploadFile" id="uploadFile" style={{visbilty:'hidden',display:'none'}} type="file"
                           name="uploadFile" onChange={(e)=>this.onFileSelected(e)}/>
                </form>
            </div>
        )
    }
};