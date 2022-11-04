import java.lang.Math.*;
import java.util.*;
public class KMeans2D {
Scanner sc = new Scanner(System.in);
int n_elements, n_clusters, flag;
int ptr[] = new int[100];
double centroid[][] = new double[100][2];
double oldCentroid[][] = new double[100][2];
static double elements[][] = new double[100][2];
double k[][][] = new double[100][100][2];
double dist(double x1, double y1, double x2, double y2) 
{
return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}
void input() throws InvalidClusterSize
{
System.out.print("\nEnter number of Clusters: ");
n_clusters = sc.nextInt();
System.out.print("\nEnter number of Points: ");
n_elements = sc.nextInt();
if (n_elements < n_clusters) 
{
throw new InvalidClusterSize(
"\nNumber of Elements should be greater than or equal to the number of Clusters\n");
}
// input points
System.out.println("\nEnter Points");
for (int i = 0; i < n_elements; i++) 
{
System.out.print("Enter Point " + (i + 1) + ": ");
elements[i][0] = sc.nextDouble();
elements[i][1] = sc.nextDouble();
}
// input centroids
System.out.println("\nEnter Centroid");
for (int i = 0; i < n_clusters; i++) 
{
System.out.print("Enter Centroid " + (i + 1) + ": ");
centroid[i][0] = sc.nextDouble();
centroid[i][1] = sc.nextDouble();
}
}
void find() {
int clusterIndex = flag = 0;
double leastDiff, tempDist;
// initialise pointer for every cluster
for (int i = 0; i < n_clusters; i++) {
ptr[i] = 0;
}
// store elements in cluster
for (int i = 0; i < n_elements; i++) {
leastDiff =
dist(elements[i][0], elements[i][1], centroid[0][0], centroid[0][1]);
clusterIndex = 0;
for (int j = 0; j < n_clusters; j++) {
tempDist =
dist(elements[i][0], elements[i][1], centroid[j][0], centroid[j][1]);
if (tempDist < leastDiff) {
leastDiff = tempDist;
clusterIndex = j;
}
}
k[clusterIndex][ptr[clusterIndex]][0] = elements[i][0];
k[clusterIndex][ptr[clusterIndex]][1] = elements[i][1];
ptr[clusterIndex]++;
}
// calculate centroid
for (int i = 0; i < n_clusters; i++) {
centroid[i][0] = centroid[i][1] = 0;
for (int j = 0; j < ptr[i]; j++) {
centroid[i][0] += k[i][j][0];
centroid[i][1] += k[i][j][1];
}
centroid[i][0] /= ptr[i];
centroid[i][1] /= ptr[i];
}
// break the recursive call if clusters are same
for (int i = 0; i < n_clusters; i++) {
if (
(oldCentroid[i][0] != centroid[i][0]) ||
oldCentroid[i][1] != centroid[i][1]
) {
flag = 1;
break;
}
}
if (flag == 1) {
for (int i = 0; i < n_clusters; i++) {
oldCentroid[i][0] = centroid[i][0];
oldCentroid[i][1] = centroid[i][1];
}
find();
}
}
void display() {
for (int i = 0; i < n_clusters; i++) {
System.out.println("\nCluster " + (i + 1) + ":");
for (int j = 0; j < ptr[i]; j++) {
System.out.println(k[i][j][0] + ", " + k[i][j][1]);
}
System.out.printf(
"Centroid %d = (%.2f, %.2f)\n",
(i + 1),
centroid[i][0],
centroid[i][1]
);
}
}
public static void main(String args[]) {
try {
KMeans2D km = new KMeans2D();
km.input();
km.find();
km.display();
System.out.println("");
} catch (InvalidClusterSize e) {
System.out.println("\nException occured: " + e);
}
}
}
class InvalidClusterSize extends Exception {
public InvalidClusterSize(String s) {
super(s);
}
}
