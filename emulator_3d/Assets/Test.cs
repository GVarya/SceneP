using System.Collections;
using UnityEngine;
using VLB;

public class LightModifier : MonoBehaviour
{
    public float lerpTime = 0.2f;
    public float updateTime = 0.01f;
    public float betweenLerpTime = 2f;

    private VolumetricLightBeam beam;
    private Color currentColor;
    private Color nextColor;
    private WaitForSeconds wait;
    private WaitForSeconds waitBetween;
    private Coroutine updateColorRoutine;

    public void Start()
    {
        beam = GetComponent<VolumetricLightBeam>();
        currentColor = beam.color;
        wait = new WaitForSeconds(updateTime);
        waitBetween = new WaitForSeconds(betweenLerpTime);
        updateColorRoutine = StartCoroutine(ChangeColorRoutine());
    }

    private IEnumerator ChangeColorRoutine()
    {
        while (true)
        {
            nextColor = Random.ColorHSV();
            for (int i = 0; i < lerpTime / updateTime; i++)
            {
                beam.color = Color.Lerp(currentColor, nextColor, i / (lerpTime / updateTime));
                yield return wait;
            }
            currentColor = nextColor;
            yield return waitBetween; 
        }
    }
}