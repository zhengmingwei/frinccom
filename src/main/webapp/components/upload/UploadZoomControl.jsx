import React from 'react';
import ReactDOM from 'react-dom';
import {Button,ButtonToolbar,Image as AmzImage} from 'amazeui-react';
import $ from 'jquery';
import Network from 'tigerfacejs-network';

/**
 * User: zyj
 * Date: 16/4/18.
 * Time: 下午4:26.
 */
var styles = {position: 'absolute', left: 0, top: 0};
export default class UploadZoomControl extends React.Component {
    constructor(props) {
        super(props);
        this.dragging = false;
        this.scale = 1;
        this.unit = 200;

        this.image = {left: 0, top: 0, width: this.unit, height: this.unit, zoom: 0};
        this.state = {
            list: [],
            currentFile: "",
            preview: "",
            time:0
        };

        this.initMask();
    }

    initMask() {
        let mask = {
            originWidth: this.props.context.targetWidth,
            originHeight: this.props.context.targetHeight
        };
        mask.ratio = this.round(mask.originWidth / mask.originHeight, 4);

        if (mask.ratio > 1) {
            // 横图
            mask.ratioX = 1;
            mask.ratioY = this.round(mask.originHeight / mask.originWidth, 4);
        } else {
            // 竖图
            mask.ratioY = 1;
            mask.ratioX = mask.ratio;
        }


        mask.width = this.unit * mask.ratioX;
        mask.height = this.unit * mask.ratioY;

        mask.left = Math.round((this.unit - mask.width) / 2);
        mask.top = Math.round((this.unit - mask.height) / 2);

        mask.scale = this.round(mask.width / mask.originWidth, 4);

        this.mask = mask;
        //console.log("mask", this.mask);
    }



    uploadFile() {
        this.props.onUploadStart();

        this.setState({time:new Date().getTime()});

        let context = this.props.context;

        let data = {
            left: Math.round(this.mask.left / this.image.scale - this.image.left / this.image.scale),
            top: Math.round(this.mask.top / this.image.scale - this.image.top / this.image.scale),
            width: Math.round(this.mask.width / this.image.scale),
            height: Math.round(this.mask.height / this.image.scale),
            targetWidth: this.mask.originWidth,
            targetHeight: this.mask.originHeight,
            //data: encodeURIComponent(dataURL)
        };

        //console.log("uploadData", data);

        this.refs.left.value = data.left;
        this.refs.top.value = data.top;
        this.refs.width.value = data.width;
        this.refs.height.value = data.height;
        this.refs.targetWidth.value = data.targetWidth;
        this.refs.targetHeight.value = data.targetHeight;

        E.doUpload(context.cdn + context.system + "/" + context.module + "/" + context.id, this.refs.uploadForm);


    }

    uploadData(dataURL) {

//console.log("图片大小", Math.round(this.mask.width / this.image.scale), Math.round(this.mask.height / this.image.scale), encodeURIComponent(dataURL).length);


    }

    selectFile(e) {
        this.refs.uploadFile.click();
    }

    round(num, digits) {
        var factor = Math.pow(10, digits);
        return Math.round(num * factor) / factor;
    }

    calcSizeInfo(img) {
        var info = {originWidth: img.width, originHeight: img.height, data: img, zoom: 0};
        let ratio = this.round(img.width / img.height, 4), ratioX, ratioY;

        if (ratio > 1) {
            // 横图
            ratioY = 1;
            ratioX = ratio;

        } else {
            // 竖图
            ratioX = 1;
            ratioY = this.round(info.originHeight / info.originWidth, 4);

        }


        //info.unit = this.unit;
        info.width = this.unit * ratioX;
        info.height = this.unit * ratioY;

        info.left = Math.round((this.unit - info.width) / 2);
        info.top = Math.round((this.unit - info.height) / 2);

        info.orginScale = info.scale = this.round(info.width / info.originWidth, 4);


        this.image = info;

        this.applySizeInfo();
    }

    zoom(scale) {
        let zoom = 1 + scale;
        this.image.width = Math.round(this.image.width * zoom);
        this.image.height = Math.round(this.image.height * zoom);
        //this.image.left = Math.round((this.unit - this.image.width) / 2);
        //this.image.top = Math.round((this.unit - this.image.height) / 2);
        this.image.scale = this.round(this.image.width / this.image.originWidth, 4);
        //this.image.scaleX = this.round(this.image.width / this.image.originWidth, 4);
        //this.image.scaleY = this.round(this.image.height / this.image.originHeight, 4);
        this.applySizeInfo();
    }

    applySizeInfo() {
        //console.log("apply", this.image);
        let $preview = $(ReactDOM.findDOMNode(this.refs.preview));
        $preview.css(this.image);
    }

    onImageLoaded(e) {
        //E.dispatchEvent("_Upload.ImageLoaded_", e.currentTarget.result);
        //console.log(e);
        //this.setState({
        //    preview: e.currentTarget.result
        //});

        var img = new Image();
        img.src = e.currentTarget.result;
        img.onload = ()=> {
            //console.log(img.width, img.height);
            this.calcSizeInfo(img);
            this.setState({
                preview: img.src
            });
        }
    }

    onFileSelected(e) {
        if (this.refs.uploadFile.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(this.refs.uploadFile.files[0]);
            reader.onload = (e)=>this.onImageLoaded(e);
        }
    }

    startDrag(e) {
        //console.log("startDrag");
        this.isDragging = true;
        let $preview = $(ReactDOM.findDOMNode(this.refs.preview));
        let offset = $preview.offset();
        this.mouseOffset = {
            x: e.pageX - offset.left, y: e.pageY - offset.top
        }
        this._stopPropagation(e);
    }

    doDragging(e) {
        if (this.isDragging) {
            //console.log("doDragging");
            let $preview = $(ReactDOM.findDOMNode(this.refs.preview));

            //console.log("mouse", this.mouseOffset, $preview.size());
            //$preview.css({'left': e.pageX - this.mouse.x, 'top': e.pageY - this.mouse.y});

            $preview.offset({'left': e.pageX - this.mouseOffset.x, 'top': e.pageY - this.mouseOffset.y});
            //console.log(this.image.left, this.image.top, $preview.offset());
            //let offset = $preview.offset();
            this.image.left = parseInt($preview.css("left"));
            this.image.top = parseInt($preview.css("top"));
        }
        this._stopPropagation(e);
    }

    stopDrag(e) {
        //console.log("stopDrag");
        this.isDragging = false;
        this._stopPropagation(e);
    }

    _stopPropagation(e) {
        if (e.stopPropagation) { //W3C阻止冒泡方法
            e.stopPropagation();
        } else {
            e.cancelBubble = true; //IE阻止冒泡方法
        }
        e.preventDefault();
    }

    drawOffscreenContext() {
        var canvas = document.createElement("canvas");
        //var canvas = ReactDOM.findDOMNode(this.refs.canvas);
        var ctx = canvas.getContext("2d");
        //ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);

        ctx.canvas.width = Math.round(this.mask.width / this.image.scale);
        ctx.canvas.height = Math.round(this.mask.height / this.image.scale);

        ctx.drawImage(
            this.image.data,
            Math.round(this.mask.left / this.image.scale - this.image.left / this.image.scale),
            Math.round(this.mask.top / this.image.scale - this.image.top / this.image.scale),
            ctx.canvas.width,
            ctx.canvas.height,
            0,
            0,
            ctx.canvas.width,
            ctx.canvas.height
            //Math.round((this.image.left-this.mask.left)/this.image.scale),
            //Math.round((this.image.top-this.mask.top)/this.image.scale),
            //this.image.originWidth,
            //this.image.originHeight
        );

        this.uploadData(canvas.toDataURL("image/png"));
    }

    render() {
        //console.log("disabled", this.props.disabled);
        //var items = [];
        //this.state.list.forEach(function (item) {
        //    items.push(<UploadItem data={item}/>);
        //}.bind(this));
        //console.log(this.image);
        let info = "";

        if(this.props.context.note) {
            info = <div style={{width:300, overflowX:'hidden', margin:'0 auto'}}>{this.props.context.note}</div>;
        }
        let preview = (<AmzImage
            ref="preview" src={this.state.preview}
            style={Object.assign(this.state.preview?{}:{display:'none'}, this.image)}
        />);
        let mask, mask1, mask2;
        if (this.mask.top > this.mask.left) {
            // 横图
            mask1 = <div
                style={{position:'absolute', backgroundColor:'rgba(0,0,0,.5)', top:0, left:0, width:this.unit, height:this.mask.top+2}}></div>
            mask2 = <div
                style={{position:'absolute', backgroundColor:'rgba(0,0,0,.5)', bottom:0, left:0, width:this.unit, height:this.mask.top+1}}></div>
        } else {
            // 竖图
            mask1 = <div
                style={{position:'absolute', backgroundColor:'rgba(0,0,0,.5)', top:0, left:0, width:this.mask.left+2, height:this.unit}}></div>
            mask2 = <div
                style={{position:'absolute', backgroundColor:'rgba(0,0,0,.5)', top:0, right:0, width:this.mask.left+1, height:this.unit}}></div>
        }
        mask = <div
            style={{position:'absolute', border:'2px dashed #fff',
            top:this.mask.top, left:this.mask.left, width:this.mask.width-2, height:this.mask.height-2}}></div>
        //let canvas = <canvas ref="canvas"
        //                     style={{
        //                     float:'left',
        //                     marginLeft:'5px',
        //                     width:this.mask.width,
        //                     height:this.mask.height,
        //                     border:'1px solid black'}}></canvas>
        return (
            <div>
                {info}
                <div onMouseDown={(e)=>this.startDrag(e)}
                     onMouseUp={(e)=>this.stopDrag(e)}
                     onMouseMove={(e)=>this.doDragging(e)}
                     onMouseOut={(e)=>this.stopDrag(e)}
                     ref="viewport" className="UploadViewport" style={{backgroundColor:'rgba(0,0,0,.5)',position:'relative'}}>
                    {preview}
                    {mask1}
                    {mask2}
                    {mask}
                </div>
                <div>
                    <button disabled={this.props.isDisabled()} onClick={(e)=>{this.zoom(0.1)}}><i className="cdn-icon icon-zoom-in"></i></button>
                    <button disabled={this.props.isDisabled()} onClick={(e)=>{this.zoom(-0.1)}}><i className="cdn-icon icon-zoom-out"></i></button>
                </div>
                <div className="UploadControlBar" style={{padding:10}}>
                    <ButtonToolbar>
                        <Button amStyle="primary" disabled={this.props.isDisabled()} onClick={(e)=>this.selectFile()}>选择</Button>
                        <Button amStyle="danger" disabled={this.props.isDisabled()} onClick={(e)=>this.uploadFile()}>确定</Button>
                    </ButtonToolbar>
                </div>
                <form ref="uploadForm" id="uploadForm" encType="multipart/form-data"
                      style={{visbilty:'hidden',display:'none'}}>
                    <input ref="uploadFile" id="uploadFile" style={{visbilty:'hidden',display:'none'}} type="file"
                           name="uploadFile" onChange={(e)=>this.onFileSelected(e)}/>
                    <input ref="left" name="left"/>
                    <input ref="top" name="top"/>
                    <input ref="width" name="width"/>
                    <input ref="height" name="height"/>
                    <input ref="targetWidth" name="targetWidth"/>
                    <input ref="targetHeight" name="targetHeight"/>
                </form>
            </div>
        )
    }
};

