import React from 'react';
import ReactDOM from 'react-dom';
import moment from 'moment';
moment.locale('zh-cn');
/**
 * User: zyj
 * Date: 16/7/8.
 * Time: 下午3:42.
 */
export default class Header extends React.Component {
    constructor(props) {
        super(props);
    }

    // componentDidMount() {
    //     this.props.dispatch(getCurrentUser());
    // }

    changePwd(){
        this.props.history.push('manager/user/password');
    }

    render() {
        const p1 = {  lineHeight: "20px",display: "block", WebkitMarginBefore: "1em", fontSize: "12px",color:"#006699"}
        const p2 = {  lineHeight: "20px",display: "block", WebkitMarginAfter: "1em", fontSize: "12px",color:"#006699"}
        const {currentUser={}} = this.props;
        return (
            <div className="header top_box ant-layout-header" style={{ backgroundImage: "url(../images/topbg.png)"}}>
                <div className="ant-col-12" ><img src="../images/logo-banner.png"/></div>
                <div className="ant-col-12" style={{textAlign: "right"}}>
                    <div><p style={p1}>{moment().format('ll dddd')}&nbsp;&nbsp;&nbsp;&nbsp;</p></div>
                    <div><p style={p2}>当前帐号:{currentUser.name} [ <a onClick={()=>this.changePwd()} target="mainFrame" className="A_blue">修改密码</a> ] [ <a href="../logout" className="A_blue">退出系统</a> ] &nbsp;&nbsp;</p></div></div>
            </div>
        )
    }
};

const Style = {
    backgroundColor: 'rgba(0, 0, 0, .3)',
    textAlign: 'center',
    padding: '5px',
    marginBottom: '5px',
    color: '#FFF'
};