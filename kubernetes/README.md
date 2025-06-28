## Project
This Module contains the Kubernetes Manifest files for the project and also the steps to expose the kubernetes dashboard

## Installation and accessing Kubernets Dashboard UI
- Enable the Kubernetes in the Docker section for enabling the management of kubernetes https://docs.docker.com/desktop/features/kubernetes/
- Install the Helm for managing the packages for the Kubernetes https://helm.sh/docs/intro/install/
- Deploying the Kubernetes dashboard UI https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/ 
  - Run these 2 steps # Add kubernetes-dashboard repository
    ` helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/ `
  - Deploy a Helm Release named "kubernetes-dashboard" using the kubernetes-dashboard chart
    ` helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard `
- Create the user to access the dashboard by following these steps based on the files in the folder . Use this repor for reference https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md
  - kubectl apply -f dashboard-adminuser.yml
  - kubectl apply -f dashboard-rolebinding.yml
  - kubectl apply -f secret.yml
- Run this command ` kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath="{.data.token}" | base64 -d ` to generate the accessToken
- Run this command to do the port forwarding to access the dashboard ` kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443 `


   

