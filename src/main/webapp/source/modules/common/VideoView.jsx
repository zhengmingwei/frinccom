import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/lib/upload/style/index.css';
/**
 * User: zhyj
 * Date: 2017/7/29.
 * Time: 10:49.
 */
export default class VideoView extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {

        const avatar = {
            width: "148px",
            height: "150px"
        }

        const antUpload = {
            display: "inlineBlock",
            background: "transparent"
        }

        const {fileInfo=""} = this.props;
        const fileInfoObj =  fileInfo ?eval("("+fileInfo+")"):{};
        const {url} = fileInfoObj;
        console.log("视频的路径："+url)
        return (
            <div style={antUpload}>
                <span style={antUpload} >
                    <a href={url} target="_blank">
                        <video style={avatar} controls="controls">
                            <source src={url} type="video/mp4"/>
                        </video>
                       {/* <video src={url} alt="" style={avatar}/>*/}
                    </a>
                </span>
            </div>

            )
        }
    };