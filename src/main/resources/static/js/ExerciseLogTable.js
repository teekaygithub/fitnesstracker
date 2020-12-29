class ExerciseLogTable extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {exerciseData: []}
    }
    
    componentDidMount() {
        fetch('/log')
        .then(response => response.json()
        .then(data => {
            console.log(data);
            this.setState({exerciseData: data});
        })).catch((error) => console.log("error:", error));
    }
    
    render() {
        const rows = this.state.exerciseData.map(logs => <tr><td>{logs.name}</td><td>{logs.duration}</td><td>{logs.date.replaceAll("-","/")}</td></tr>);
        return (
            <div className="col-auto">
                <table className="table table-striped table-hover table-bordered table-responsive">
                    <thead className="thead-dark">
                        <tr>
                            <th>Exercise</th>
                            <th>Duration (minutes)</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </table>
            </div>
        );
    }
}

const exerciseTableContainer = document.getElementById("test");
ReactDOM.render(<ExerciseLogTable />, exerciseTableContainer);